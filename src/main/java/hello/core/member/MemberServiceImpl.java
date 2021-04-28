package hello.core.member;

public class MemberServiceImpl implements MemberService{

    //Appconfig 적용 전
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //적용 후 생성자를 만든다
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도 메소드
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
