<%@ include file="/WEB-INF/jsp/includeJsp.jsp" %>

<s:layout-render 
	name="/WEB-INF/jsp/page/abstractTracktacularPage.jsp"
	title="Tracktacular > Subscribe"
	>

	<s:layout-component name="contentHeading">		
		Subscribe	
	</s:layout-component>
	
	<s:layout-component name="contentBody">
		
		<!-- Stripe -->
		<script type="text/javascript" src="https://js.stripe.com/v2/"></script>
  	
  		<script type="text/javascript">
			Stripe.setPublishableKey('pk_live_KieqKfK5bNL9ZdfT6OXluejx');
		</script>
		
		<section>
		
			<s:form id="payment-form" class="dialog-form" beanclass="com.tracktacular.web.page.account.SubscribeActionBean">
				<input type="hidden" name=_eventName value="subscribe" />
				<i:action name="subscribeAction"/>
				
				<div class="box-shadow">
					
					<div class="dialog-form-heading ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix">
						<span class="left ui-dialog-title">Subscribe</span>
					</div>
					
					<div class="dialog-form-body">
						
						<div class="center">
							Organization for the price of a movie ticket. Subscribe now!
						</div>
				
						<div class="center" style="margin: 25px 125px 0 125px;">
							
							<div class="box-shadow center" style="margin-bottom: 15px;">
								<div class="dialog-form-heading ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"
									style="color:black;border:0;background: #5C9CCC url(/static/tracktacular/img/lib/redmond/ui-bg_gloss_gold-wave_55_5c9ccc_500x100.png) 50% 50% repeat-x;">
									<span class="center ui-dialog-title">Monthly Subscription</span>
								</div>
								<div style="padding: 15px 15px 0 15px">
									<b style="color: #28A328; font-size: 28px;">$10</b> <br/>
									per month <br/><br/>
									<b>Unlimited Trackers</b> <br/>
									<b>Guaranteed Privacy</b> <br/>
									<b>Military Grade Encryption</b> <br/>
									<b>One Click Data Export</b> <br/>
									<b>No Hassle Cancellation</b> <br/>
									<br/>
									<c:if test="${actionBean.context.referrerUserEid != null}">
										<div class="center" style="margin-top: 10px;">	
											You've got a friend here... <br/>
											Enjoy <b>50%</b> off your first payment!
										</div>
									</c:if>
									<br/>
								</div>
							</div>
							
						</div>
						<div>
							
							
							
							<s:errors />
							<div class="center validation-errors payment-errors" style="margin-top:15px;"></div>
							
							<div class="cf">
							
								<div class="left" style="margin:0 15px 10px 35px;">
									<label for="payment-number" class="primary required">Card Number</label>				
									<label for="payment-number" class="secondary">No dashes or hyphens please</label>				
									<input id="payment-number" type="text" maxlength="20" size="32" data-stripe="number"/>
								</div>
							
								<div class="left" style="margin:0 25px;">
									<label for="payment-exp" class="primary required">Expiration</label>				
									<label for="payment-exp" class="secondary">Card Expiration Date</label>				
									
									<select id="payment-exp" style="padding:1px;height:21px;margin-top:5px;"  data-stripe="exp_month">  
										<option value="">MM</option>
										<c:forEach var="month" items="${actionBean.expirationMonths}">
											<option value="${month}">${month}</option>
								        </c:forEach>
								    </select>
									
									<select style="padding:1px;height:21px;margin-top:5px;"  data-stripe="exp_year">  
										<option value="">YYYY</option>
										<c:forEach var="year" items="${actionBean.expirationYears}">
											<option value="${year}">${year}</option>
								        </c:forEach>
								    </select>
								</div>
							
								<div class="left">
									<label for="payment-cvc" class="primary required">Card CVC</label>				
									<label for="payment-cvc" class="secondary">Card Security code</label>				
									<input id="payment-cvc" type="text" maxlength="4" size="6" data-stripe="cvc"/>
								</div>
							
							</div>
							
						</div>
							
					</div>
					
					<hr>
					
					<div class="dialog-form-footer cf">
						<div class="dialog-form-footer-img">
							<img id="dialog-form-busy" class="none" alt="Loading"
								src="/static/tracktacular/img/page/busy-indicator-form.gif" >
						</div>
						<div class="dialog-form-footer-button">
							<input id="payment-form-submit" name="subscribe" value="Submit" 
								class="dialog-form-button ui-state-default ui-priority-primary ui-corner-all" type="submit">
						</div>
					</div>
					
				</div>
			</s:form>
			
		</section>
		
		<div class="center">
			<img src="/static/tracktacular/img/page/stripe.png"/>
			<br/><br/>
			<span class="subtitle">Your payment information is protected by the most stringent level of PCI certification available.</span>
		</div>
			
	</s:layout-component>	
	
	<%-- JS Resources --%>	
	<s:layout-component name="jsResources">
		
		<%-- JavaScript CDN Resources --%>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>			
		
		<%-- JavaScript Fallback Resources --%>
		<script>//<![CDATA[
           	window.jQuery||document.write('<script src="/static/tracktacular/js/lib/jquery-1.7.1.min.js"><\/script>')//]]>
		</script>					
		<script>//<![CDATA[
           	window.jQuery.ui||document.write('<script src="/static/tracktacular/js/lib/jquery.ui-1.8.16.min.js"><\/script>')//]]>
           </script>			
		
		<%-- JavaScript Resources --%>
		<script src="/static/tracktacular/js/page-${actionBean.pageVersion}.js"></script>			 	
	 	<script src="/static/tracktacular/js/template-${actionBean.pageVersion}.js"></script>
	 	
 	 </s:layout-component>
	
</s:layout-render>