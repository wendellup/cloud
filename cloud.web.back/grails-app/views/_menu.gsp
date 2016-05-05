<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<span class="menu_top"><img src="<%=request.getContextPath()%>/images/menu_bg_t.jpg"/></span>
<ul class="menu_inner">
	<g:each	var="parentMenu" in="${menuMap['0']}">
    	<li class="close"><span></span><strong>${parentMenu.menuName}</strong>
        <ul style="display: none">
        	<g:each var="menu" in="${menuMap[parentMenu.id+'']}">
        		<g:if test="${menu.menuUrl == ''}">
					<li class="close_li close" onclick="changeLastli(this,event)">
						<a href="javascript:void(0);">${menu.menuName}</a>
						<ul class="last" style="display:none" >
							<g:each var="childMenu" in="${menuMap[menu.id+'']}">
								<g:if test="${childMenu.menuUrl==''}">
									<li class="close_li close" onclick="changeLastli(this,event)">
										<a href="javascript:void(0);">${childMenu.menuName}</a>
										<ul class="last" style="display:none" >
											<g:each var="subchildMenu" in="${menuMap[childMenu.id+'']}">
												<li onclick="changeIframe(this,event,'${request.contextPath}${subchildMenu.menuUrl}')">
													<a href="javascript:void(0)">${subchildMenu.menuName}</a>
												</li>
											</g:each>
										</ul>
									</li>
								</g:if>
								<g:if test="${childMenu.menuUrl!=''}">
									<li onclick="changeIframe(this,event,'${request.contextPath}${childMenu.menuUrl}')">
										<a href="javascript:void(0)">${childMenu.menuName}</a>
									</li>
								</g:if>
							</g:each>
						</ul>
					</li>
				</g:if>
        		<g:else>
	        		<li onclick="changeIframe(this, event, '${request.contextPath}${menu.menuUrl}')">
		                <a href="javascript:void(0)">${menu.menuName}</a>
		            </li>
        		</g:else>
        	</g:each>
        </ul>
    </li>
	</g:each>
</ul>
<span class="menu_bom"></span>