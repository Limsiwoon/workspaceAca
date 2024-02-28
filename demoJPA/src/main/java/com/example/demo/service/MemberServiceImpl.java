/*
 * package com.example.demo.service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.example.demo.domain.BoardDTO; import
 * com.example.demo.domain.MemberDTO; import com.example.demo.entity.Member;
 * import com.example.demo.repository.MemberRepository;
 * 
 * import mapperInterface.MemberMapper; import pageTest.SearchCriteria;
 * 
 * //@Component //@Service public class MemberServiceImpl implements
 * MemberService { // 전역 변수 처리 private final MemberRepository repository;
 * 
 * 
 * //selectList
 * 
 * @Override public List<Member> selectList() { return repository.findAll();
 * //repository에 갖고 있는 함수. }
 * 
 * // selectOne
 * 
 * @Override public MemberDTO selectOne(String id) { return
 * repository.selectOne(id); }
 * 
 * @Override public List<MemberDTO> selectOne2(int jno) { return
 * repository.selectOne2(jno); }
 * 
 * 
 * // insert
 * 
 * @Override public int insert(MemberDTO dto) { return repository.insert(dto); }
 * 
 * // update
 * 
 * @Override public int update(MemberDTO dto) { return repository.update(dto); }
 * 
 * @Override public int delete(String id) { return mapper.delete(id); } }
 */
