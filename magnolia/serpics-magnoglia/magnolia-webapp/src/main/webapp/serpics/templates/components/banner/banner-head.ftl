<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
					<ol class="carousel-indicators">
					[#list cmsfn.children(content, "mgnl:area") as carouselArea ]
						[#list cmsfn.children(carouselArea, "mgnl:component") as carouselComponent ]
							[#if carouselComponent_index == 0]
								<li data-target="#slider-carousel" data-slide-to="${carouselComponent_index}" class="active"></li>
							[#else]
								<li data-target="#slider-carousel" data-slide-to="${carouselComponent_index}" class=""></li>
							[/#if]
						[/#list]	
					[/#list]
					</ol>
						<div class="carousel-inner">
								[@cms.area name="serpics-carousel" contextAttributes={"baseSite":ctx.baseSite}/]
							
						</div>
						
						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
					
				</div>
			</div>
		</div>
	</section>