$(function() {
	var themes = [ {
		value : 'default',
		text : 'Default',
		group : 'Base'
	}, {
		value : 'gray',
		text : 'Gray',
		group : 'Base'
	}, {
		value : 'metro',
		text : 'Metro',
		group : 'Base'
	}, {
		value : 'bootstrap',
		text : 'Bootstrap',
		group : 'Base'
	}, {
		value : 'black',
		text : 'Black',
		group : 'Base'
	}, {
		value : 'metro-blue',
		text : 'Metro Blue',
		group : 'Metro'
	}, {
		value : 'metro-gray',
		text : 'Metro Gray',
		group : 'Metro'
	}, {
		value : 'metro-green',
		text : 'Metro Green',
		group : 'Metro'
	}, {
		value : 'metro-orange',
		text : 'Metro Orange',
		group : 'Metro'
	}, {
		value : 'metro-red',
		text : 'Metro Red',
		group : 'Metro'
	}, {
		value : 'ui-cupertino',
		text : 'Cupertino',
		group : 'UI'
	}, {
		value : 'ui-dark-hive',
		text : 'Dark Hive',
		group : 'UI'
	}, {
		value : 'ui-pepper-grinder',
		text : 'Pepper Grinder',
		group : 'UI'
	}, {
		value : 'ui-sunny',
		text : 'Sunny',
		group : 'UI'
	} ];
	$('#demo').panel();
	$('#cb-theme').combobox({
		groupField : 'group',
		data : themes,
		editable : false,
		panelHeight : 'auto',
		onChange : changeThemeFun,
		onLoadSuccess : function() {
			if ($.cookie('easyuiThemeName')) {
				$(this).combobox('setValue', $.cookie('easyuiThemeName'));
			} else {
				$(this).combobox('setValue', 'gray');
			}
		}
	});
});
function changeThemeFun(theme) {
	var link = $('#easyuiTheme');
	link.attr('href', G_CTX_PATH + '/plugins/jquery-easyui/themes/' + theme
			+ '/easyui.css');

	$.cookie('easyuiThemeName', theme, {
		expires : 7
	});
}
