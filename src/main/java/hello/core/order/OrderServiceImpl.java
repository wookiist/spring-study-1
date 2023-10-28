 package hello.core.order;

 import hello.core.annotation.MainDiscountPolicy;
 import hello.core.discount.DiscountPolicy;
 import hello.core.member.Member;
 import hello.core.member.MemberRepository;
 import lombok.RequiredArgsConstructor;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Qualifier;
 import org.springframework.stereotype.Component;

 @Component
 public class OrderServiceImpl implements OrderService {

     // final 키워드는 생성할 때 만들어지면 그 뒤로는 안 바뀜
     // 초기에 만들어주거나, 생성자에서만 세팅할 수 있음.
     // 개발자가 실수로 생성자를 누락할 수도 있겠지.
     // 생성자를 누락하지 않았어도, 그 안에서 값 세팅을 안 하는 경우도 잡아줌 (컴파일러에서)

     private final MemberRepository memberRepository;
     // final은 무조건 값이 할당이 (또는 생성자) 되어야 해서, 일단 이렇게 만들기 위해 final을 지움
     // Null 에다가 점찍으면 발생하는 에러 ㅋㅋㅋ npe.
     private final DiscountPolicy discountPolicy;

//     public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//         this.memberRepository = memberRepository;
//         this.discountPolicy = discountPolicy;
//     }

     @Autowired
     public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
         this.memberRepository = memberRepository;
         this.discountPolicy = discountPolicy;
     }

     @Override
     public Order createOrder(Long memberId, String itemName, int itemPrice) {
         Member member = memberRepository.findById(memberId);
         int discountPrice = discountPolicy.discount(member, itemPrice);
         // discount 가격되는거는 모르겠고, 그건 discountPolicy 니가 다 알아서 해줘! (단일책임원칙, SIP)
         // 만약에 여기서 할인에 대한 무언가를 정의했다면, 할인 방식등이 바뀌면, OrderService를 수정해야 하게 되잖아!

         return new Order(memberId, itemName, itemPrice, discountPrice);
     }

     // 테스트 용도
     public MemberRepository getMemberRepository() {
         return memberRepository;
     }
 }
