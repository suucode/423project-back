<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<div class="wanteds_page">
	<div class="search_wanted">
		<div class="all_position">
			<div class="select_all">
				<select class="all">
					<option selected>전체</option>
					<option>백앤드 개발자</option>
					<option>웹 퍼블리셔</option>
					<option>프론트앤드 개발자</option>
				</select>
			</div>
			<div class="select_position">직군을 선택해주세요</div>
		</div>
		<div class="selects">
			<div class="select_region">
				<select class="region">
					<option selected>전체</option>
					<option>부산</option>
				</select>
			</div>
			<div class="select_career">
				<select class="career">
					<option selected>전체</option>
					<option>신입</option>
				</select>
			</div>
			<div class="select_skill">
				<select class="skill">
					<option selected>기술스택</option>
					<option>Java</option>
				</select>
			</div>
			<div class="select_sort">
				<select class="sort">
					<option selected>정렬</option>
					<option>최신순</option>
					<option>인기순</option>
				</select>
			</div>
		</div>
	</div>
	<div class="overBox">
		<div class="liked">
			<div class="liked_wanted">
				<div class="liked_title">♥ 내가 좋아요한 공고들</div>
				<div class="wanteds">
					<c:forEach var="wantedsLikeList" items="${wantedsLikeList}">
						<div class="wanted">
							<a href="#">
								<div class="picture">
									<img src="https://picsum.photos/seed/picsum/200/300">
								</div>
								<div class="wanted_text">
									<div class="text">
										<ul>
											<li class="title">
												<h2>${wantedsLikeList.title}</h2>
											</li>
											<li class="content">
												<p>${wantedsLikeList.detail}</p>
											</li>
										</ul>
									</div>
									<div class="company_intro">
										<p class="company_region">${wantedsLikeList.regionCodeName}</p>
										<p class="company_name">${wantedsLikeList.companyName}</p>
									</div>
								</div>
							</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="all_wanteds">
			<div class="wanteds">
				<c:forEach var="wantedsAllList" items="${wantedsAllList}">
					<div class="wanted">
						<a href="#">
							<div class="picture">
								<img src="https://picsum.photos/seed/picsum/200/300">
							</div>
							<div class="wanted_text">
								<div class="text">
									<ul>
										<li class="title">
											<h2>${wantedsAllList.title}</h2>
										</li>
										<li class="content">
											<p>${wantedsAllList.detail}</p>
										</li>
									</ul>
								</div>
								<div class="company_intro">
									<p class="company_region">${wantedsAllList.regionCodeName}</p>
									<p class="company_name">${wantedsAllList.companyName}</p>
								</div>
							</div>
						</a>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
</body>
</html>