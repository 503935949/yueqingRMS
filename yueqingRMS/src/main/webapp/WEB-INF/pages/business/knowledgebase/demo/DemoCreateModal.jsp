<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!-- OVERVIEW -->
<div class="modal fade" id="demoCreateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="demoCreateModalLabel">
        <yrms:demo resName="i18n_business_demo_create_titile" attr="true" >
        	${i18n_business_demo_create_titile}
        </yrms:demo>
        </h4>
      </div>
      <!-- modal-body start -->
      <div class="modal-body">
        <!-- form start -->
        <form  class="form-horizontal" >
	          <div class="form-group">
	          	<label for="demoCreateEmail" class="col-xs-2 col-sm-2 col-md-2 control-label">Email</label>
			    <div class="col-xs-4 col-sm-4 col-md-4">
			       <input name="email" class="form-control" id="demoCreateEmail" placeholder="Email"  >
			    </div>
			    <label for="demoCreatePassword" class="col-xs-2 col-sm-2 col-md-2 control-label"><span class="text-danger">*</span>Password</label>
			    <div class="col-xs-4 col-sm-4 col-md-4">
			       <input name="password" class="form-control" id="demoCreatePassword" placeholder="Password">
			    </div>
	          </div>
	          <div class="form-group">
	            <label for="demoCreateMessage" class="col-xs-2 col-sm-2 col-md-2 control-label">Message</label>
	            <div class="col-xs-10 col-sm-10 col-md-10">
	            	<textarea class="form-control" id="demoCreateMessage"></textarea>
	            </div>
	          </div>
	          <div class="form-group">
			    <label for="demoCreateFile"  class="col-xs-2 col-sm-2 col-md-2 control-label" >File input</label>
			    <div class="col-xs-10 col-sm-10 col-md-10">
				    <input type="file" id="demoCreateFile">
				    <p class="help-block">Example block-level help text here.</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for=""  class="col-xs-2 col-sm-2 col-md-2 control-label" >Check</label>
			    <div class="col-xs-10 col-sm-10 col-md-10">
				    <div class="checkbox">
					    <label class="checkbox-inline">
						  <input type="checkbox" name="Check" id="demoCreateCheck1" value="1"> 男
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" name="Check" id="demoCreateCheck2" value="2"> 女
						</label>
						<label class="checkbox-inline">
						  <input type="checkbox" name="Check" id="demoCreateCheck3" value="0"> 无
						</label>
					</div>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for=""  class="col-xs-2 col-sm-2 col-md-2 control-label" >Check</label>
			    <div class="col-xs-10 col-sm-10 col-md-10">
				    <div class="checkbox">
					    <label class="radio-inline">
						  <input type="radio" name="inlineRadioOptions" id="demoCreateSax1" value="option1"> 1
						</label>
						<label class="radio-inline">
						  <input type="radio" name="inlineRadioOptions" id="demoCreateSax2" value="option2"> 2
						</label>
						<label class="radio-inline">
						  <input type="radio" name="inlineRadioOptions" id="demoCreateSax3" value="option3"> 3
						</label>
					</div>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for=""  class="col-xs-2 col-sm-2 col-md-2 control-label" >Check</label>
			    <div class="col-xs-10 col-sm-10 col-md-10">
				    <div class="checkbox">
					    <label>
					      <input type="checkbox"> Check me out
					    </label>
					</div>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-xs-2 col-sm-2 col-md-2 control-label sr-only" for="exampleInputAmount">Amount (in dollars)</label>
			    <div class="col-xs-2 col-sm-2 col-md-2">
			    </div>
			    <div class="col-xs-10 col-sm-10 col-md-10">
				    <div class="input-group">
				      <div class="input-group-addon">$</div>
				      <input type="text" class="form-control" id="exampleInputAmount" placeholder="Amount">
				      <div class="input-group-addon">.00</div>
				    </div>
			    </div>
			  </div>
			  <div class="form-group">
				  <label class="col-xs-2 col-sm-2 col-md-2 control-label sr-only" for="exampleInputAmount">Amount (in dollars)</label>
				    <div class="col-xs-2 col-sm-2 col-md-2">
				    </div>
				    <div class="col-xs-10 col-sm-10 col-md-10">
					  <div class="radio">
						  <label>
						    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
						    Option one is this and that&mdash;be sure to include why it's great
						  </label>
						</div>
						<div class="radio">
						  <label>
						    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
						    Option two can be something else and selecting it will deselect option one
						  </label>
						</div>
						<div class="radio disabled">
						  <label>
						    <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3" disabled>
						    Option three is disabled
						  </label>
						</div>
					</div>
			  </div>
			  <div class="form-group">
			    <label for=""  class="col-xs-2 col-sm-2 col-md-2 control-label" >Check</label>
			    <div class="col-xs-10 col-sm-10 col-md-10">
				    <select class="form-control">
					  <option>1</option>
					  <option>2</option>
					  <option>3</option>
					  <option>4</option>
					  <option>5</option>
					</select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for=""  class="col-xs-2 col-sm-2 col-md-2 control-label" >Check</label>
			    <div class="col-xs-10 col-sm-10 col-md-10">
				    <select  multiple class="form-control">
					  <option>1</option>
					  <option>2</option>
					  <option>3</option>
					  <option>4</option>
					  <option>5</option>
					</select>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for=""  class="col-xs-2 col-sm-2 col-md-2 control-label" >Email</label>
			    <div class="col-xs-10 col-sm-10 col-md-10">
			      <p class="form-control-static">email@example.com</p>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputPassword" class="col-xs-2 col-sm-2 col-md-2 control-label">Password</label>
			    <div class="col-xs-10 col-sm-10 col-md-10">
			      <input type="password" class="form-control" id="inputPassword" placeholder="Password">
			    </div>
			  </div>
			  
			  	
        </form>
        <!-- form end -->
      </div>
      <!-- modal-body end -->
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary">
      		${i18n_object_determine}
      	</button>
      	<button type="button" class="btn btn-danger">
      		${i18n_object_clear}
      	</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">
        	${i18n_object_shutDown}
        </button>
      </div>
    </div>
  </div>
</div>
<!-- OVERVIEW end-->
		
