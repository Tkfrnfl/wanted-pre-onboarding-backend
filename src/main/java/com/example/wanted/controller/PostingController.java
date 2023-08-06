package com.example.wanted.controller;


import com.example.wanted.Impl.UserDetailsImpl;
import com.example.wanted.dto.GetAllPostingForm;
import com.example.wanted.dto.PostingForm;
import com.example.wanted.dto.SignUpForm;
import com.example.wanted.entitiy.Posting;
import com.example.wanted.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posting")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @PostMapping("/newPosting")
    public ResponseEntity<String> newPosting(@RequestBody PostingForm postingForm,@AuthenticationPrincipal User user) throws Exception {

        return ResponseEntity.ok(postingService.newPostingService(user.getUsername(),postingForm.getDetail()));
    }

    @GetMapping("/getAllPosting")
    public ResponseEntity<Page<Posting>> getAllPosting(@RequestBody GetAllPostingForm getAllPostingForm) throws Exception {

        return ResponseEntity.ok(postingService.getAllPostingService(getAllPostingForm.getPage(),getAllPostingForm.getSize()));
    }

    @GetMapping("/getOnePosting")
    public ResponseEntity<Posting> getOnePosting(@RequestBody Map<String, String> postingId) throws Exception {

        return ResponseEntity.ok(postingService.getOnePostingService(Long.parseLong( postingId.get("postingId"))));
    }
    @PatchMapping ("/patchPosting")
    public ResponseEntity<Posting> patchPosting(@RequestBody Map<String, String> postingId,@AuthenticationPrincipal User user) throws Exception {

        return ResponseEntity.ok(postingService.patchPostingService(Long.parseLong( postingId.get("postingId")), postingId.get("detail"),user.getUsername()));
    }
    @DeleteMapping ("/deletePosting")
    public ResponseEntity<String> deletePosting(@RequestBody Map<String, String> postingId,@AuthenticationPrincipal User user) throws Exception {

        return ResponseEntity.ok(postingService.deletePostingService(Long.parseLong( postingId.get("postingId")),user.getUsername()));
    }


}
