# **심플 퀘스트** [![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fhoos007%2FUnivAssignment-SimpleQuest&count_bg=%2379C83D&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)
안드로이드 일정 관리 어플

이 프로젝트는 대학교 3학년 1학기 모바일 프로그래밍 과목의 기말평가로 진행한 프로젝트 입니다.
## 프로젝트 개요
### 이름
심플 퀘스트

### 기획 배경
직접 일정관리 앱을 개발하고 싶어서 진행한 프로젝트입니다. 일정 관리 앱을 개발하면서 여러 레이아웃을 구상하고 DB를 사용해보는 경험을 통해 많이 배울 수 있겠다는 생각 때문에 이 아이디어를 채택했습니다.

### 주요 기능
1. 일정 저장기능
2. 중요한 일정 체크 기능
3. TODAY페이지
4. 캘린더 기능

## 프로젝트 기간
2022.06.14 ~ 2022.06.21

## 참여인원
2인 프로젝트
| 이름 | 깃허브 | 담당 역할 및 기능 | 비고 |
| ---- | ---- | ---- | ---- |
| 이효승 | [hoos007](https://github.com/hoos007) | 설계 및 구현 | 신라대학교 컴퓨터소트웨어공학부 3학년 |
| 윤남운 |  | 설계 및 구현 | 신라대학교 컴퓨터소트웨어공학부 3학년 |

### 이효승
1. 하단탭
    1. 하단 탭 구성 + 원형 버튼
    2. 앱 전체 프래그먼트
2. Today 페이지
    1. DB연동 리사이클러 뷰
    2. 리사이클러 뷰 요소 클릭
3. DB
    1. 스키마 설계
    2. DB, 테이블 생성
    3. DB연동

### 윤남운
1. Calender 페이지
    1. 캘린더 뷰
    2. 캘린더 뷰 디자인
    3. 날짜 선택
2. Add 페이지
    1. 페이지 구성
    2. 타임 피커
    3. 데이터 피커

## 기술 스택 및 환경
### 환경
<img src="https://img.shields.io/badge/android-3DDC84?style=for-the-badge&logo=android&logoColor=white">

### 개발
<img src="https://img.shields.io/badge/Android studio-3DDC84?style=for-the-badge&logo=androidstudio&logoColor=white"> <img src="https://img.shields.io/badge/java-FFFFFF?style=for-the-badge&logo=openjdk&logoColor=black">
<img src="https://img.shields.io/badge/sqlite-003B57?style=for-the-badge&logo=sqlite&logoColor=white">

## 실행화면
Today 페이지 - 일정이 없는 경우

![Today Empty](https://github.com/hoos007/UnivAssignment-SimpleQuest/assets/113767998/a393b1b6-8b5a-4ce2-bb96-3552f1b0f028)

Today 페이지 - 일정이 있는 경우

![Today](https://github.com/hoos007/UnivAssignment-SimpleQuest/assets/113767998/362b06a8-9330-45e7-abb3-7345f31b0669)

Add 페이지

![Add](https://github.com/hoos007/UnivAssignment-SimpleQuest/assets/113767998/301655e3-c291-4801-a0b6-0cd4fe7ed509)

Calender 페이지 - 일정이 없는 경우

![Calender Empty](https://github.com/hoos007/UnivAssignment-SimpleQuest/assets/113767998/3043f611-caee-463a-884d-87c116ec348c)

Calender 페이지 - 일정이 있는 경우

![Calender](https://github.com/hoos007/UnivAssignment-SimpleQuest/assets/113767998/00053372-9a86-4a64-ab6e-f67dec31f49c)

## 기능구현
1. Today 페이지
    1. Main Activity의 Fregment1
    2. RecyclerView
2. Calender 페이지
    1. Main Activity의 Fregment2
    2. RecyclerView
3. Add 페이지
    1. popup형식으로 띄우기 위해서 새로운 Activity 생성
4. DB
    1. sqlite 사용
    2. DB 테이블
        <img width="592" alt="DB" src="https://github.com/hoos007/UnivAssignment-SimpleQuest/assets/113767998/790052ee-f3f2-47db-9cd5-2b91e081f8f5">