<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!-- OVERVIEW -->
<div class="modal fade" id="demoDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">
			${i18n_business_demo_delete_titile}
		</h4>
      </div>
      <div class="modal-body">
        <p class="text-muted">
			${i18n_business_dialog_deleteConfirm}
		</p>
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
		
