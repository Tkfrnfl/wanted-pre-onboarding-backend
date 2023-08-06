package com.example.wanted.service;

import com.example.wanted.config.AesKeyConfig;
import com.example.wanted.dto.SignUpForm;
import com.example.wanted.entitiy.User;
import com.example.wanted.jwt.JwtTokenProvider;
import com.example.wanted.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;
    public static String alg = "AES/CBC/PKCS5Padding";
    @Autowired
    private final AesKeyConfig aesKeyConfig;
//    private  String iv = aesKeyConfig.getKey().substring(0, 16); // 16byte
    private String iv;

    @Transactional
    public String signUpService(SignUpForm signUpForm) throws Exception {
        //System.out.println(aesKeyConfig.getKey());
        iv=aesKeyConfig.getKey().substring(0, 16);
        String formCheck=inputCheck(signUpForm);
        if(formCheck=="정상"){
            User user=new User();

            user.setEmail(signUpForm.getEmail());
            String aesPassword=encrypt(signUpForm.getPassword(),iv) ; //aes 암호화
            user.setPassword(aesPassword);
            userRepository.save(user);
            return "회원가입 성공";
        }
        else {
            return formCheck;
        }
    }

    @Transactional
    public String signInService(SignUpForm signUpForm) throws Exception {
        iv=aesKeyConfig.getKey().substring(0, 16);
        String formCheck=inputCheck(signUpForm);
        if(formCheck=="정상"){
            User user=new User();

            user.setEmail(signUpForm.getEmail());
            String aesPassword=encrypt(signUpForm.getPassword(),iv) ; //aes 암호화
            user.setPassword(aesPassword);
            if(userRepository.existsByEmailAndPassword(signUpForm.getEmail(),user.getPassword())){
                String createToken= jwtTokenProvider.createToken(signUpForm.getEmail());
                return  createToken;
            }
            else{
                return "회원정보 불일치";
            }

        }
        else {
            return formCheck;
        }
    }

    public String inputCheck(SignUpForm signUpForm){
        if(signUpForm.getEmail().contains("@")){ // 이메일@ 포함 체크
            if(signUpForm.getPassword().length()>=8){  //비밀번호 8자리 체크
                return "정상";
            }
            else{
                return "비밀번호는 8자리 이상입니다.";
            }
        }
        else {
            return "이메일은 @포함되야합니다.";
        }
    }

    public String encrypt(String text,String iv) throws Exception {
        System.out.println(iv);
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String cipherText,String iv) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(iv.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }
}
