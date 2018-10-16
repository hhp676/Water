
function initPersonalCenter(sex) {
	$("#userInfoForm [value='"+sex+"']").attr("checked","checked");
	
	$('#changeUserInfoForm').validate({
		rules:{
			userName: { required: true ,maxlength:32},
			engName: {maxlength:32},
			email:{email:true},
			mobile:{mobile:true}
			
		}
	});
	
	$("#changeUserInfoForm [tag='ok']").click(function(){
		//验证表单--------------------------------------------------
		if(!$('#changeUserInfoForm').validate().form()) return false;
		Hg.getJson("/sys/sysUser/editLoginUser",$("#changeUserInfoForm").serializeArray(),function(data){
			if (!data.success) {
				$.messager.alert("保存数据失败",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#personalCenter_layout").parent().window("close");
				});
			}
		});

	});
	
	$('#changePassForm').validate({
		rules:{
			oldPwd: { required: true},
			newPwd: {required:true,rangelength:[5,20]},
			confirmNewPwd: {equalTo: "#newPwd"}
		}
	});
	$("#changePassForm [tag='ok']").click(function(){
		//验证表单--------------------------------------------------
		if(!$('#changePassForm').validate().form()) return false;
		Hg.getJson("/sys/sysUser/changePwd",$("#changePassForm").serializeArray(),function(data){
			if (!data.success) {
				Hg.showErrorMsg("changePassForm",data.data);
			} else {
				$.messager.ok("保存数据成功!",function(){
					$("#personalCenter_layout").parent().window("close");
				});
			}
		});

	});
}


initPersonalCenter(sex);
		