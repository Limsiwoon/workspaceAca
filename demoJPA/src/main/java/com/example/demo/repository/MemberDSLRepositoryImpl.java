package com.example.demo.repository;

import static com.example.demo.entity.QJo.jo;
import static com.example.demo.entity.QMember.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDSLRepositoryImpl implements MemberDSLRepository {

	private final JPAQueryFactory jPAQueryFactory;

	// 1) Entity return
	// => Q클래스 return
	// => Parameter로 전달된 조원들만 출력하기

	@Override
	public List<Member> findMemberJnoDSL(int jno) {
		// TODO Auto-generated method stub
		// member 변수가 존재 하지 않음 -> 위에 정의 필요 (static import => 미리 import하여 편하게 활용하도록 하는 것)
		// => import static
		// 기본 import 구문은 '패키지 명시 없이 클래스를 사용'하게 해 주는데,
		// import static 구문은 한 단계 더 들어가 '클래스 명시 없이 static변수나 static메서드를 사용'하게 해줌
		// selectFrom 작성하면 select 와 from을 알아서 작성.
		return jPAQueryFactory.selectFrom(member).where(member.jno.eq(jno).and(member.point.goe(100)))
				.orderBy(member.age.desc()).fetch();
	}

	// 2) Join & DTO return
	// => QueryDSL 에서 DTO 적용하기
	// 메모장 QueryDSL사용법.txt 참고
	// => 4종류 방법중
	// 2.1) Setter 접근 , 2.2) 필드 직접접근 적용

	// 2.1) Setter 접근
	// => MemberDto의 setter 를 호출해서 , Dto 의 멤버변수에 injection 해주는 방식.
	// => Projections.bean(~~~) 로 접근
	@Override
	public List<MemberDTO> findMemberJoinDSL() {
		// TODO Auto-generated method stub
		return jPAQueryFactory.select(
				// join 결과의 member.XX 각각 가져오는 것임.
				Projections.bean(MemberDTO.class, member.id, member.name, member.jno, jo.jname, jo.project)) // bean
				.from(member).leftJoin(jo).on(member.jno.eq(jo.jno)) // .eq() ()안의 내용과 .앞의 내용 동일
				.fetchJoin().fetch();
	}

	// 2.2) 필드 직접 접근
	// => DTO 에 setter/getter 없어도 가능
	// => Projections.fields(~~~) 로 접근
	// -> 그러므로 DTO 에 setter/getter 없어도 가능하며 MemberDto의 멤버변수에 값이 injection 된다.
	@Override
	public List<MemberDTO> findMemberJoinDSL2() {
		// TODO Auto-generated method stub
		return jPAQueryFactory.select(
				// join 결과의 member.XX 각각 가져오는 것임.
				Projections.fields(MemberDTO.class, member.id, member.name, member.jno, jo.jname, jo.project))
				.from(member).leftJoin(jo).on(member.jno.eq(jo.jno)) // .eq() ()안의 내용과 .앞의 내용 동일
				.fetchJoin().fetch();
	}
}
