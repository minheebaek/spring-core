package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository();//인터페이스만 가지고 있으면 널포인트익셉션 발생
    //9분 31초 다형성
    private final MemberRepository memberRepository;//추상화에만 의존, DIP 지킴, 구체적인거 모름

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) { //join 호출하면 다형성에 의해 memberRepository가 아니라 MemoryMemberRepository의 save가 호출된다
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
