[#assign categoryId = ""]
[#if model.root.content == "Category"]
[#assign categoryId = ctx.categoryId!]
[/#if]
<div class="left-sidebar" ng-controller="categoryController" >
<div ng-if="categoryData" ng-init="openParentCategory(${categoryId})">
</div>
	<h2>Category</h2>
	<div class="panel-group category-products">
		<!--category-productsr-->
		<div class="panel panel-default" ng-repeat="category in categoryData">
		<div class="panel-heading">
		<ul>
			<li >
				<p  ng-if="category.childCategoryNumber !=0" ng-class="{ 'fa fa-minus badge pull-right': categoryData[$index].active, 'fa fa-plus badge pull-right': !categoryData[$index].active }" ng-click="getChild(category.id,$index,category)">
        		</p>
        		<h4 class="panel-title">
        			<a href="${ctx.contextPath}/${ctx.baseSite}/Category?categoryId={{category.id}}&categoryName={{category.code}}">
        				{{category.name || category.code}}
        			</a>
        		</h4>
        		<div ng-class="{ 'panel-collapse': categoryData[$index].active, 'panel-collapse collapse': !categoryData[$index].active }">
        			<div class="panel-body">
						<ul >
                			<li ng-if="category.childCategoryNumber !=0" ng-repeat="subCat in categoryData[$index].subCategory"  ng-show="category.active">
                    			<a href="${ctx.contextPath}/${ctx.baseSite}/Category?categoryId={{subCat.id}}&categoryName={{subCat.code}}">{{subCat.name || subCat.code}}</a>
                			</li>
            			</ul>
            		</div>
            	</div>
        	</li>
    	</ul>
		</div> 
		</div>
	</div>
</div>
	<!--/category-products-->