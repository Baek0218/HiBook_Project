# HiBook 중고책 거래 사이트
### 호스팅 사이트 : http://itwillbs7.cafe24.com/teamProject/main.do
#### 1. 개발도구 : Eclipse, Github  
#### 2. 개발언어 : Java(OpenJDK 11.0.2), MySQL(8.0.17), JavaScript
#### 3. 라이브러리 : jQuery(3.6.3)
#### 4. 웹 애플리케이션도구 : JSP(MVC model2)
#### 5. 웹 호스팅 : cafe24

***

#### 개발 기간 : 1월 30일 ~ 2월 24일
#### 역할 : 깃허브 관리, 클라우디너리 연동
#### 개발 페이지 : 메인 페이지, 중고거래 페이지 

### <상세 코드>
#### 메인 페이지  
1. Model 
    - 메인 리스트 [MainPro.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/main/action/MainPro.java)  

2. View    
    - 메인 [Main.jsp](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/webapp/main/main.jsp)  
    - 헤더 [Header.jsp](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/webapp/inc/header.jsp)  
    - 푸터 [Footer.jsp](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/webapp/inc/footer.jsp)  

3. Controller  
    - 컨트롤러 [MainFrontController.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/main/action/MainFrontController.java)  

#### 중고거래 페이지 
1. Model 
    - 중고거래 DAO [MarketDAO.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/db/MarketDAO.java)  
    - 중고거래 DTO [MarketDTO.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/db/MarketDTO.java)  
    - 중고거래 글상세 [MarketContent.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/action/MarketContent.java)   
    - 중고거래 리스트(기본) [MarketList.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/action/MarketList.java)  
    - 중고거래 리스트(최신/조회/인기) [MarketSortList.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/action/MarketSortList.java)   
    - 중고거래 글수정 [MarketUpdateForm.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/action/MarketUpdateForm.java), [MarketUpdatePro.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/action/MarketUpdatePro.java)  
    - 중고거래 글작성 [MarketWritePro.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/action/MarketWritePro.java) 
    - 중고거래 글삭제 [MarketDeletePro.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/action/MarketDeletePro.java)
    - 중고거래 찜하기 [MarketWishPro.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/action/MarketWishPro.java)
    
2. View  
    - 중고거래 글상세 페이지 [MarketContent.jsp](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/webapp/market/MarketContent.jsp)   
    - 중고거래 리스트(기본) [MarketList.jsp](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/webapp/market/MarketList.jsp)  
    - 중고거래 리스트(최신/조회/인기) [MarketSortList.jsp](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/webapp/market/MarketSortList.jsp)   
    - 중고거래 글수정 페이지 [MarketUpdateForm.jsp](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/webapp/market/MarketUpdateForm.jsp)
    - 중고거래 글작성 페이지 [MarketWriteForm.jsp](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/webapp/market/MarketWriteForm.jsp)  

3. Controller  
    - 컨트롤러 [MarketFrontController.java](https://github.com/MEMOZ00/Project_team2/blob/cafe24/teamProject/src/main/java/com/itwillbs/market/action/MarketFrontController.java)

테스트용추가은숙
