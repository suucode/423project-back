package site.metacoding.miniproject1.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.miniproject1.domain.codes.CareersCodeDao;
import site.metacoding.miniproject1.domain.codes.PositionsCodeDao;
import site.metacoding.miniproject1.domain.codes.RegionsCodeDao;
import site.metacoding.miniproject1.domain.codes.SkillsCodeDao;
import site.metacoding.miniproject1.domain.wanteds.WantedsDao;
import site.metacoding.miniproject1.web.dto.response.codes.AllCodesDto;
import site.metacoding.miniproject1.web.dto.response.mySkills.WantedsSkillsDto;
import site.metacoding.miniproject1.web.dto.response.wanteds.KeywordDto;
import site.metacoding.miniproject1.web.dto.response.wanteds.PagingDto;
import site.metacoding.miniproject1.web.dto.response.wanteds.PagingWantedsListDto;
import site.metacoding.miniproject1.web.dto.response.wanteds.WantedDetailAndCompanyDto;
import site.metacoding.miniproject1.web.dto.response.wanteds.WantedDetailDto;
import site.metacoding.miniproject1.web.dto.response.wanteds.WantedsListDto;

@RequiredArgsConstructor
@Service
public class WantedsService {
	private final WantedsDao wantedsDao;
	private final PositionsCodeDao positionsCodeDao;
	private final SkillsCodeDao skillsCodeDao;
	private final RegionsCodeDao regionsCodeDao;
	private final CareersCodeDao careersCodeDao;
	private final MySkillsService mySkillsService;
	
	public List<WantedsListDto> findAllByCompanyId(Integer id) {
		List<WantedsListDto> wantedsListDtosPS = wantedsDao.findAllByCompanyId(id);
		return wantedsListDtosPS;
	}
	
	public WantedDetailAndCompanyDto findByCompanyIdAndAllWanteds(Integer id) {
		WantedDetailAndCompanyDto wantedDetailAndCompanyDtoPS = new WantedDetailAndCompanyDto();
		wantedDetailAndCompanyDtoPS.setWantedDetailDtoPS(findByIdToDetail(id));
		int companyNum = wantedDetailAndCompanyDtoPS.getWantedDetailDtoPS().getCompanyId();
		
		if(wantedDetailAndCompanyDtoPS.getWantedDetailDtoPS() == null) return null;
		
		wantedDetailAndCompanyDtoPS.setWantedsListDtosPS(findAllByCompanyId(companyNum));
		return wantedDetailAndCompanyDtoPS;
	}
	
	public WantedDetailDto findByIdToDetail(Integer id) {
		WantedDetailDto wantedDetailDtoPS = wantedsDao.findByIdToDetail(id);
		if(wantedDetailDtoPS == null) return null;
		
		List<WantedsSkillsDto> wantedsSkillsDtosPS = mySkillsService.findMySkillByWantedId(id);
		wantedDetailDtoPS.setMySkills(wantedsSkillsDtosPS);
		return wantedDetailDtoPS;
	}
	
	public PagingWantedsListDto pagingWantedsList(KeywordDto keywordDto) {
		if(keywordDto.getPage() == null) keywordDto.setPage(0);;
		int startNum = keywordDto.getPage() * 16;
		keywordDto.setStartNum(startNum);
		PagingWantedsListDto pagingWantedsListDtoPS = new PagingWantedsListDto();
		
		pagingWantedsListDtoPS.setPagingDto(paging(keywordDto));
		pagingWantedsListDtoPS.setWantedsListDtos(findAllToSort(keywordDto));
		return pagingWantedsListDtoPS;
	}
	
	public PagingDto paging(KeywordDto keywordDto) {
		PagingDto pagingPS = wantedsDao.paging(keywordDto);
		
		final int blockCount = 16;
		int currentBlock = keywordDto.getPage()/ blockCount;
		int startPageNum = 1 + blockCount * currentBlock;
		int lastPageNum = 16 + blockCount * currentBlock;
		
		if(pagingPS.getTotalPage() < lastPageNum) {
			lastPageNum = pagingPS.getTotalPage();
		}
		
		pagingPS.setBlockCount(blockCount);
		pagingPS.setCurrentBlock(currentBlock);
		pagingPS.setStartPageNum(startPageNum);
		pagingPS.setLastPageNum(lastPageNum);
		
		return pagingPS;
	}
	
	public List<WantedsListDto> findAllToSort(KeywordDto keywordDto){
		List<WantedsListDto> wantedsListPS = wantedsDao.findAllToSort(keywordDto);
		for(int i = 0; i < wantedsListPS.size(); i++) {
			List<WantedsSkillsDto> wantedsSkillsPS = mySkillsService.findMySkillByWantedId(i);
			if(wantedsSkillsPS != null) {
				wantedsListPS.get(i).setMySkills(wantedsSkillsPS);
			}
		}
		return wantedsListPS;
	}
	
	public AllCodesDto findAllCodes() {
		AllCodesDto allCodesDto = new AllCodesDto();
		allCodesDto.setPositionsCodeDtos(positionsCodeDao.findAll());
		allCodesDto.setCareersCodeDtos(careersCodeDao.findAll());
		allCodesDto.setRegionsCodeDtos(regionsCodeDao.findAll());
		allCodesDto.setSkillsCodeDtos(skillsCodeDao.findAll());
		return allCodesDto;
	}
	
	public List<WantedsListDto> findByLike(Integer userId){
		//유저가 있는지 확인하기
		//findById로 확인할것, 없으면 null넘기기
		if(userId == 0) return null;
		
		List<WantedsListDto> wantedsDtosPS = wantedsDao.findAllLike(userId);
		return wantedsDtosPS;
	}
	
	public WantedsListDto findBestHot() {
		WantedsListDto wantedsDtoPS = wantedsDao.findBestHot();
		return wantedsDtoPS;
	}

	public List<WantedsListDto> findAllHot() {
		List<WantedsListDto> wantedsDtosPS = wantedsDao.findAllHot();
		return wantedsDtosPS;
	}

	public List<WantedsListDto> findAllByposition(Integer positionCodeId) {
		// 포지션이 있는지 확인하기
		if (positionsCodeDao.findById(positionCodeId) == null)
			return null;

		List<WantedsListDto> wantedsDtosPS = wantedsDao.findAllByposition(positionCodeId);
		return wantedsDtosPS;
	}
}
