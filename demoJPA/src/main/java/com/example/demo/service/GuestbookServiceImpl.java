package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.domain.GuestbookDTO;
import com.example.demo.domain.PageRequestDTO;
import com.example.demo.domain.PageResultDTO;
import com.example.demo.entity.Guestbook;
import com.example.demo.repository.GuestbookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Service //service 임을 알려주는 것. 
@Log4j2
@RequiredArgsConstructor	
//	=> 생성자 주입 @AllArgsConstructor사용하지만, 보다 정확하게 할 때 사용 
//	=> 각필드에 대해 1개의 매개변수가 있는 생성자를 생성함.
//	=> 모든 컬럼에 적용되느냐? no. 초기화 되지 않은 모든 final 필드에만 적용됨. 
public class GuestbookServiceImpl implements GuestbookService {

	//래파지토리를 먼저 생성해 두어야 가능 => 값을 할당하지 않을 때에는 오류
	//	=> 애너테이션을 처리를 해주지만, Autowired 사용하지 않고, 
	//	=> JPA Sql 처리를 위해 정으 하는 경우에는 생성자를 주입 받아야 함. 
	private final GuestbookRepository repository;
	
	//	** JPA Pagable 을 이용한 Paging 
	public PageResultDTO<GuestbookDTO, Guestbook> pageList(PageRequestDTO requestDTO) {
		
		// => 조건 완성 
		//Pageable API 지원해주는 인터페이스 
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending()); // 인자로 정렬 조건 첨부
		
		// => repository 실행 
		// 위에서 정렬된 pageable을 받아 pageable 에 담기 
		// pageable을 인자로 하여 List 뽑기. result에 담음
		Page<Guestbook> result = repository.findAll(pageable);
		
		// => Function<EN,DTO> 정의 
		Function<Guestbook, GuestbookDTO> fn = ( entity -> entityToDto(entity) );
		// => Service 에 정의한 entityToDto 메서드를 이용해서 entity를 Dto로
		
		return new PageResultDTO<>(result, fn);
	}// pageList

	
	
	@Override
	public List<Guestbook> selectList() {
		// TODO Auto-generated method stub
		return repository.findAll();
		
	}

	@Override
	public Guestbook selectOne(Long gno) {
		
		// ** Optional<T> 레퍼클래스 객체 
		// => Java8부터 Optional<T>클래스를 사용해 NullPointerException(이하 NPE)를 방지할수 있도록 지원.
	  	//     즉, Optional<T>는 null이 올수 있는 값을 감싸는 Wrapper클래스로,
		//			참조하더라도 NPE가 발생하지 않도록 도와줌.
	   	//     제공되는 메소드로 복잡한 조건문 없이 NPE를 회피할 수 있어록 해줌
	   	// => sPresent() : Optional객체에 저장된 값이 null인지 확인 ( false 면 null )
	   	// => get() : Optional객체에 저장된 값 제공
	    	// => 참고 https://esoongan.tistory.com/95
		
		Optional<Guestbook> result = repository.findById(gno);
		if( result.isPresent() ) return result.get() ; // Optional객체에 저장된 값 제공
		else return null ;
		// Optional 이용하는 이유 : null point exception을 방지 하기 위해서 
		
		// repository.findAllById(gno); 리턴 타입이 guestbook 이 아님. 
		// optional 타입임 => Optional<Guestbook> 로 만듦
		
	}

	@Override
	public Long register(GuestbookDTO dto) {
		
		log.info("** register,dto => " + dto);
		Guestbook entity = dtoToEntity(dto);
		repository.save(entity); // 처리후 entity를 return 함 
		
		return entity.getGno(); // 저장된 자동생성된 키값을 return함 
	}

	@Override
	public void delete(Long gno) {
		repository.deleteById(gno);
	}

	
}//class
