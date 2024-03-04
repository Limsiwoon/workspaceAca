package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;

@Transactional
@Repository
public class MyRepositoryImpl implements MyRepository {

	// EntityManager
	// jpa 라이브러리에서 다 작성되어 있으나,실제로 직접 만들기 위해서는 entityManager 를 사용함.
	private final EntityManager em;

	// 생성자
	public MyRepositoryImpl(EntityManager em) {
		this.em = em;
	}

	// @Override
	// => parameter 적용 X
	public List<Member> emMemberList2() {

		// return type = typed query
		// createQuery("쿼리문", 결과타입 )

		return em.createQuery("select m from Member m order by id asc", Member.class).getResultList();
		// => JPQL 적용
		// => "select * from Member order by id asc" 500오류 발생
		// entity 를 통해 접근하기 때문에 * 사용 금지, 엘리어스를 상요해 컬럼명 접근

	}

	@Override
	// => parameter 적용
	public List<Member> emMemberList() {
		return em.createQuery("select m from Member m where jno<:jno", Member.class).setParameter("jno", 3)
				.getResultList();
	}

	@Override
	public Member emMemberDetail(String id) {
		return em.createQuery("select m from Member m where id=:ii", Member.class).setParameter("ii", id)
				.getSingleResult();
	}

}
