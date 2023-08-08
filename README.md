# wanted-pre-onboarding-backend
### 문정현
### 실행방법
실행방법
ec2 테스트시:

3.35.139.232:8080 엔드포인트 주소를 이용하여 api 문서대로 테스트


로컬 테스트시:


docker, docker-compose 설치후 해당 프로젝트의 docker-compose.yml 파일을 받은 경로에서
docker-compose up 실행

or
해당프로젝트 클론후 intellij실행

### 테이블 구조
![구조도](https://github.com/Tkfrnfl/wanted-pre-onboarding-backend/assets/58983957/eed3fc5e-0be0-442c-95d3-70eefde03226)

### 데모영상
https://drive.google.com/file/d/1yfEEgyVrMmIhAOQwcThlBfK6PuvH2ZAF/view?usp=sharing
### 구현방법 및 간략한 설명
스프링부트,jdbc를 기반으로한 jpa를 이용하여 코드를 구성했습니다. postingService, userService 크게 두 부분으로 나누어 구현하였습니다.
구현후 github push를 하면 테스트코드 체크를 한후, 도커 이미지로 업로드하고, EC2에서 해당 이미지를 받아 docker-compose를 구성하게됩니다.

### 클라우드 환경
![구성도](https://github.com/Tkfrnfl/wanted-pre-onboarding-backend/assets/58983957/39d6a944-f573-4cdf-b0d3-674cf113af4f)


### API 명세
[api.pdf](https://github.com/Tkfrnfl/wanted-pre-onboarding-backend/files/12293500/api.pdf)


** 비용문제로 11:00~ 19:00 만 서버를 열어두고있습니다.
