[#assign categoryId = ctx.categoryId!""]
<div class="left-sidebar" ng-controller="categoryController" ng-init="getChildData(${model.root.content.categoryId})">
<div ng-if="categoryData.length" ng-init="openParentCategory(${categoryId})">
</div>
	<h2>${model.root.content.categoryName}</h2>
	<div class="panel-group category-products">
		<!--category-products-->
		<div class="panel panel-default" ng-repeat="category in categoryData">
		<div class="panel-heading">
		<ul>
			<li >
				<p  ng-if="category.childCategoryNumber !=0" ng-class="{ 'fa fa-minus badge pull-right': categoryData[$index].active, 'fa fa-plus badge pull-right': !categoryData[$index].active }" ng-click="getChild(category.id,$index,category)">
        		</p>
        		<h4 class="panel-title">
        			<a href="${cmsfn.link(model.root.content)}?categoryId={{category.id}}&categoryName={{category.name || category.code}}">
        				{{category.name || category.code}}
        			</a>
        		</h4>
        		<div ng-class="{ 'panel-collapse': categoryData[$index].active, 'panel-collapse collapse': !categoryData[$index].active }">
        			<div class="panel-body">
						<ul >
                			<li ng-if="category.childCategoryNumber !=0" ng-repeat="subCat in categoryData[$index].subCategory"  ng-show="category.active">
                    			<a href="${cmsfn.link(model.root.content)}?categoryId={{subCat.id}}&categoryName={{subCat.name || subCat.code}}">{{subCat.name || subCat.code}}</a>
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