<%@ include file="../../include/importTag.jsp"%>
<div class="card">
	<div class="card-body">
		<form:form
			class="form form-horizontal"
			modelAttribute="customerDto"
			action="${server_name}/customer/setup"
			method="POST">
			<div class="row">
				<form:hidden path="id" />
				<div class="row">
					<div class="col-md-12 form-group">
						<div class="col-md-6">
							<label class="col-sm-4 control-label">Name</label>
							<div class="col-sm-8">
								<form:input
									class="form-control"
									path="name"
									autocapitalize="off"
									autocomplete="off"
									spellcheck="false"
									autocorrect="off"
									required="required" />
							</div>
						</div>
						<div class="col-md-6">
							<label class="col-sm-4 control-label">Phone No</label>
							<div class="col-sm-8">
								<form:input
									class="form-control"
									path="phoneNo"
									autocapitalize="off"
									autocomplete="off"
									spellcheck="false"
									autocorrect="off"
									required="required" />
							</div>
						</div>
					</div>
						<div class="col-md-6">
							<label class="col-sm-4 control-label">Address</label>
							<div class="col-sm-8">
								<form:input
									class="form-control"
									path="address"
									autocapitalize="off"
									autocomplete="off"
									spellcheck="false"
									autocorrect="off"
									required="required" />
							</div>
						</div>
					</div>
					<div class="col-md-12 form-group">
						<div class="col-md-6">
							<label class="col-sm-4 control-label">Status</label>
							<div class="col-sm-8">
								<form:select
									class="custom-select"
									required="required"
									path="status">
									<form:option value="ACTIVE">Active</form:option>
									<form:option value="INACTIVE">Inactive</form:option>
								</form:select>
							</div>
						</div>
						<div class="col-md-6">
							<label class="col-sm-4 control-label"></label>
							<div
								class="col-sm-8" style="text-align: right;">
								<a
									href="setup"
									class="btn btn-warning"
									style="margin-right: 5px;">Clear</a>
								<button
									type="submit"
									class="btn btn-info"
									style="margin-right: 5px;">
									<c:if test="${customerDto.id != null }">Update</c:if>
									<c:if test="${customerDto.id == null }">Save</c:if>
								</button>
								<%-- <c:if test="${customerDto.id != null }">
									<a
										href="search"
										class="btn btn-primary">Back</a>
								</c:if> --%>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
<script>
	
</script>
