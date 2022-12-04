# Blog 시스템 개발

## 프로젝트 개요
```
스프링 부트를 기반으로 하여 블로그를 생성합니다.
H2 데이터 베이스를 사용하여 create_table.sql 파일을 이용하여 테이블을 생성합니다.
블로그는 인덱스 페이지, 로그인, 블로그 생성, 블로그 메인, 블로그 관리 페이지, 카테고리 관리 페이지, 포스트 등록 페이지 등
블로그에 기본적으로 가지고 있는 기능들을 토이 프로젝트를 통해 직접 제작해봅니다.
```

## 프로젝트 기능 명세
### 1. 인덱스 페이지(index.jsp) 구현
>   - [x] 1) <로그인> / <블로그등록> / <블로그바로가기> 링크 구현 (상호배타적으로)  
로그인(system/login.jsp) 링크 구현 : 로그인을 하지 않은 상태에서는 <로그인> 링크만 보이며, <블로그등록>, <블로그바로가기> 링크는 안보인다.  
블로그 등록(blog/insertBlog.jsp) 링크 구현 : 블로그를 소유하지 않은 사용자가 로그인에 성공했다면 <블로그 등록> 링크만 보일 것  
블로그바로가기(blog/blogMain.jsp) 링크 구현 : 블로그를 소유한 사용자가 로그인에 생공했다면 <블로그바로가기> 링크만 보여야 한다.  
> - [x] 2) 검색 기능 구현 : 검색 버튼을 눌렀을 때 블로그 목록이 출력되며, 생성된 블로그가 하나도 없다면 검색 결과 테이블 자체가 보이지 않도록(빈 테이블도 노출되지 않도록) 구현
/ 검색조건은 블로그 제목, 태그
> - [x] 3) 블로그 검색 결과 테이블에서 블로그 제목 링크를 클릭하면 해당 블로그 메인 화면(blog/blogMain.jsp)으로 이동할 수 있다.

 
### 2. 로그인 구현
> - [x] 4) 로그인 버튼을 누르면 성공/실패 여부와 무관하게 무조건 메인페이지 (welcome.jsp) 페이지로 이동하도록 구현
> - 로그인 성공시 : 블로그 소유에 따라 <블로그등록> / <블로그바로가기> 링크가 표시됨
> - 로그인 실패시 : 다시 로그인 화면으로 이동할 수 있도록 로그인 링크만 표시됨 (블로그 생성 및 내 블로그 가기 링크 미표시)

### 3. 블로그 생성 구현
> - [x] 5)
> - 블로그 제목만 입력하고 [확인] 버튼을 클릭하면 블로그가 생성되고 블로그 메인 페이지(blog/blogMain.jsp)로 이동한다.  
> - 블로그를 생성할 때 사용자가 입력하는 것은 블로그 제목뿐이며, 나머지 컬럼에는 기본 값이 적절히 등록되도록 한다.  
> - 블로그가 생성될 때는 기본적으로 ‘미분류’라는 기본 카테고리를 갖도록 한다. 즉 새로운 블로그가 생성되는 순간 카테고리 테이블에 해당 블로그와 관련된 ‘미분류’ 카테고리도 생성되도록 한다.  
> - 정상적으로 블로그가 생성되고 나면 메인 화면(welcome.jsp)으로 이동하여 <블로그바로가기> 링크를 클릭하여 블로그 메인 화면(blog/blogMain.jsp)으로 이동할 수 있도록 한다.  

### 4. 블로그 메인 구현
> - [x] 6) 포스트 목록은 카테고리에 상관없이 최근에 등록한 포스트 목록부터 출력된다. 하지만 오른쪽에 카테고리를 클릭하면 해당 카테고리에 등록된 포스트만 출력되어야 한다. (즉 카테고리 별 검색이 되어야 함)
> - [x] 7) 포스트 제목 오른쪽에는 포스트를 수정할 수 있는 <수정>과 <삭제> 링크가 제공된다.  <수정>를 누르면 포스트 상세 및 수정 화면(post/getPost.jsp)으로 이동하고, <삭제>를 누르면 해당 포스트가 삭제되고 다시 현재 화면(blog/blogMain.jsp)으로 돌아온다.  
(이때 포스트 삭제 이전에 사용자가 선택했던 카테고리 목록이 유지될 수 있어야 한다.)
> - [x] 8) 블로그 메인 화면 우측 상단에는 <로그인>, <로그아웃> 링크를 상호 배타적으로 제공한다. 만약 로그인을 하지 않았다면 <로그인> 링크만 보여야 하며, 정상적으로 로그인에 성공한 상태라면 <로그아웃> 링크와 더불어 <블로그관리>, <블로그메인> 링크가 보여야 한다.
> - [x] 9) 현재 사용자가 블로그 소유자라면 <블로그관리>와 <블로그메인> 링크도 제공한다.

### 5. 블로그 관리 페이지
> - [x] 10)
> - 상단에는 블로그 제목과 태그가 출력된다. 그리고 블로그 관리 화면으로 왔다는 것은 로그인 성공한 상태를 의미하므로 <로그아웃> 링크와 <블로그메인> 링크만 보여야 한다.  
당연히 <블로그메인> 링크를 클릭하면 블로그 메인 화면(blog/blogMain.jsp)으로 이동한다.
> - 중간에는 <기본설정>, <카테고리>, <글작성>, <블로그삭제> 메뉴가 제공된다.  
<카테고리>는 현재 블로그에 등록된 카테고리를 관리하는 메뉴이며, <글작성>은 새로운 포스트를 등록하고 관리하는 메뉴다. 
> - 추가로 <삭제요청> 메뉴도 제공할 수 있는데, <삭제요청> 메뉴를 클릭하면 현재 블로그의 상태 컬럼(STATUS)의 값을 ‘운영’에서 ‘삭제요청’으로 변경한다. 그리고 이후에 관리자가 블로그를 검색하여 해당 블로그를 삭제하도록 한다. (삭제 요청 기능은 필수 기능은 아님)
> - 블로그 제목, 블로그 태그를 수정하고 [확인] 버튼을 클릭하면 해당 블로그를 수정하고 블로그 메인 페이지(blog/blogMain.jsp)로 이동한다. 당연히 수정한 정보가 반영되어야 한다. 

### 6. 카테고리 관리 페이지
> - [x] 11) 블로그 관리 페이지에서 <카테고리> 링크를 클릭했을 때 제공되는 화면이며, 상단에 현재 블로그에 속한 카테고리 목록이 출력된다.
> - [x] 12) 미분류 카테고리는 수정이나 삭제가 불가능하다.
> - [x] 13) 카테고리 목록 오른쪽 끝에 X 이미지를 클릭하여 카테고리를 삭제할 수 있다. 카테고리가 삭제되고 나면 목록에서 제외되며, 아래쪽에는 [카테고리 등록] 화면이 제공된다.
> - [x] 14) 카테고리 목록 아래에는 [카테고리 등록] 이나 [카테고리 수정] 화면이 제공되는데, 처음 카테고리 관리 화면으로 이동하면 [카테고리 등록] 화면이 제공되며,  
카테고리 목록에서 특정 카테고리 이름을 클릭하면 [카테고리 수정] 화면으로 변경되고 수정을 위한 상세 정보가 출력된다.
> - [x] 15) 카테고리 정보를 수정하고 나면 카테고리 관리화면으로 돌아오고 목록 아래 화면은 [카테고리 등록] 화면으로 초기화된다.

### 7. 포스트 등록 페이지
> - [x] 16) 기본적으로 포스트의 제목, 카테고리, 내용을 입력하고 확인 버튼을 누르면 해당 포스트가 등록되고 블로그 메인 페이지(blog/blogMain.jsp)로 이동한다.
> - [x] 17) 블로그 메인 화면에서 포스트 제목 오른쪽에 <수정> 링크를 클릭하면 해당 포스트를 수정할 수 있도록 포스트 상세 화면(post/getPost.jsp)으로 이동한다.
> - [x] 18) 포스트를 수정하고 수정하기 버튼을 클릭하면 블로그 메인 화면(blog/blogMain.jsp)으로 이동한다.
