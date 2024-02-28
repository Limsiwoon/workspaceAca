package com.example.demo.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;

//JPA 의 CRUD 메서드
//	=> ~Repository 를 통해 JPA 의 EntityManager 에 접근됨.
//	=> EntityManager : 영속 계층에 접근하여 엔티티에 대한 DB 작업을 제공함.
//	=> 주요 메서드
//    - Insert : save(엔티티 객체)
//    - Select : findAll(), findById(키), getOne(키) ..
//    - Update : save(엔티티 객체)
//    - Delete : deleteById(키), delete(엔티티 객체)
//	=> Insert, Update 모두 save(엔티티 객체)를 사용하는 이유
//     - JpaRepository 의 save는 비교후 없으면 insert, 
//     있으면 update를 동작시키고, entity를 return.   
//     - deleteById(키) 삭제의 경우에도 select 후 없으면 ~~DataAccessException 발생시키고
//     있으면 삭제하고 void 로 정의되어 return값 없음. 
public interface GuestbookService {
	
	// ** JPA Pageable 을 이용한 Pageing 기능
	PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO);

	// => JPA CRUD 구현
	// entity로 넘어오게 됨 =>
	List<Guestbook> selectList(); //읽음
	Guestbook selectOne(Long gno); //하나만 읽음
	Long register(GuestbookDTO dto); //insert, update 모두 사용 // 삽입, 수정
	void delete(Long gno); //삭제 
	
	//dtoToEntity() 와  entityToDto() 메서드 추가  => 둘을 분리하여 사용하기 위해서 작성함
	
	// dtoToEntity() 
	// insert, update 위해 주로 사용되므로 regDate, modDate 는 제외됨
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
							.gno(dto.getGno())
							.title(dto.getTitle())
							.content(dto.getContent())
							.writer(dto.getWriter())
							.build();
		return entity;
	}
	
	// entityToDto()
	// 결과 출력시 주로 사용되므로 regDate, modDate 포함임
	default GuestbookDTO entityToDto(Guestbook entity) {
		return GuestbookDTO.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
	}
	
}
