# 크리스마스 프로모션

---

## 프로젝트 개요

날짜와 메뉴 정보를 받아서 혜택 정보를 출력해주는 기능을 개발하는 프로젝트입니다.

#### 메뉴

```
<애피타이저>
양송이수프(6,000), 타파스(5,500), 시저샐러드(8,000)

<메인>
티본스테이크(55,000), 바비큐립(54,000), 해산물파스타(35,000), 크리스마스파스타(25,000)

<디저트>
초코케이크(15,000), 아이스크림(5,000)

<음료>
제로콜라(3,000), 레드와인(60,000), 샴페인(25,000)
```

#### 달력

![image.png](..%2Fimage.png)

#### 혜택

- 크리스마스 디데이 할인
    - 이벤트 기간: 2023.12.1 ~ 2023.12.25
    - 1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가
    - 총주문 금액에서 해당 금액만큼 할인  
      (e.g. 시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인)
- 평일 할인(일요일~목요일): 평일에는 디저트 메뉴를 메뉴 1개당 2,023원 할인
- 주말 할인(금요일, 토요일): 주말에는 메인 메뉴를 메뉴 1개당 2,023원 할인
- 특별 할인: 이벤트 달력에 별이 있으면 총주문 금액에서 1,000원 할인
- 증정 이벤트: 할인 전 총주문 금액이 12만 원 이상일 때, 샴페인 1개 증정
- 이벤트 기간: '크리스마스 디데이 할인'을 제외한 다른 이벤트는 2023.12.1 ~ 2023.12.31 동안 적용


- 총혜택 금액에 따라 다른 이벤트 배지를 부여
    - 5천 원 이상: 별
    - 1만 원 이상: 트리
    - 2만 원 이상: 산타

#### 요청 사항

- 총주문 금액 10,000원 이상부터 이벤트 적용
- 음료만 주문 불가능
- 메뉴는 한 번에 최대 20개까지만 주문 가능
- 날짜 입력
    - 1 이상 31 이하의 숫자만 가능
- 메뉴 입력
    - 메뉴판에 있는 메뉴만 가능
    - 메뉴의 개수는 1개 이상
    - 메뉴 형식으로만 입력 가능
    - 중복 메뉴 불가

---

## 개발 환경

- ```Java 17```
- ```JDK 17.0.8```
- IDE : IntelliJ 2023.2.1 (Community Edition)

---

## 기능

### 출력

- [x] 주문 메뉴 출력
    - 메뉴와 개수를 출력한다.
- [x] 금액 출력
    - 금액을 형식에 맞춰 출력한다.

### 입력

- [x] 숫자 입력
    - 숫자 형식이 아닐 경우 오류를 발생시킨다.
- [x] 메뉴 입력
    - 메뉴 형식으로 분할한 뒤 넘긴다.

### 플래너

- [x] 날짜 저장
    - 1 ~ 31 범위가 아닌 경우 오류를 발생시킨다.
- [x] 메뉴 저장
    - 메뉴가 1개 이상 20개 이하가 아닌 경우 오류를 발생시킨다.
    - 음료만 있는 경우 오류를 발생시킨다.
- [x] 혜택 확인
    - 총주문 금액을 확인한다.
    - 요일을 확인한다.
    - 요일에 따라 혜택을 적용한다.
        - 혜택 적용 여부를 확인한다.
        - 헤택 할인 금액을 계산한다.
        - 총혜택 금액에 더한다.
- [x] 배지 확인
    - 총혜택 금액에 따라 배지를 설정한다.

### 주문 목록

- [x] 메뉴와 개수를 목록에 추가한다.
    - 메뉴가 올바른지 확인한다.
    - 개수가 올바른지 확인한다.
- [x] 전체 가격을 계산한다.
- [x] 전체 개수를 계산한다.

### 혜택 목록

- [x] 혜택 여부를 확인한다.
- [x] 전체 혜택을 계산한다.
- [x] 전체 할인 금액을 계산한다.