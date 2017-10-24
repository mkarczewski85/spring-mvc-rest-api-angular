<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">New employee form:  </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.employee.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="firstName">First name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.employee.firstName" id="firstName" class="form-control input-sm" placeholder="Enter first name" required ng-minlength="2"/>
	                        </div>
	                    </div>
	                </div>

	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="lastName">Last name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.employee.lastName" id="lastName" class="form-control input-sm" placeholder="Enter last name" required ng-minlength="2"/>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="salary">Salary</label>
	                        <div class="col-md-7">
	                            <input type="number" ng-model="ctrl.employee.salary" id="salary" class="form-control input-sm" placeholder="Enter salary" required ng-pattern="ctrl.onlyNumbers"/>
	                        </div>
	                    </div>
	                </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-lable" for="email">E-mail</label>
                            <div class="col-md-7">
                                <input type="text" ng-model="ctrl.employee.email" id="email" class="form-control input-sm" placeholder="Enter e-mail" required"/>
                            </div>
                        </div>
                    </div>

	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.employee.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">List of employees </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>FIRST NAME</th>
		                <th>LAST NAME</th>
		                <th>SALARY</th>
		                <th>E-MAIL</th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="e in ctrl.getAllEmployees()">
		                <td>{{e.id}}</td>
		                <td>{{e.firstName}}</td>
		                <td>{{e.lastName}}</td>
		                <td>{{e.salary}}</td>
		                <td>{{e.email}}</td>
		                <td><button type="button" ng-click="ctrl.editEmployee(e.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeEmployee(e.id)" class="btn btn-danger custom-width">Remove</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
</div>