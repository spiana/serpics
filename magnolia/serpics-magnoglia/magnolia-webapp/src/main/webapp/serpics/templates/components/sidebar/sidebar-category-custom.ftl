<div class="left-sidebar" >
	<h2>Category</h2>
	<div class="panel-group category-products">
		<!--category-products-->
		[#list cmsfn.children(model.root.content, "mgnl:page") as category ]
		<div class="panel panel-default">
		<div class="panel-heading">
		<ul>
			<li >
        		<h4 class="panel-title">
        			<a href="${cmsfn.link(category)}">
        				${category.title!}
        			</a>
        		</h4>
        	</li>
    	</ul>
		</div> 
		</div>
		[/#list]
	</div>
</div>
	<!--/category-products-->