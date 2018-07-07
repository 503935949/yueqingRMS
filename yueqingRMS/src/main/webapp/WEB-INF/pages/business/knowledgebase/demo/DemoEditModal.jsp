<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!-- OVERVIEW -->
<div class="modal fade" id="demoEditModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">
			${i18n_business_demo_edit_titile}
		</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">Recipient:</label>
            <input type="text" class="form-control" id="recipient-name">
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Message:</label>
            <textarea class="form-control" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-primary">
      		${i18n_object_determine}
      	</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">
        	${i18n_object_shutDown}
        </button>
      </div>
    </div>
  </div>
</div>
<!-- OVERVIEW end-->
		
