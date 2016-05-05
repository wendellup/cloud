/** 弹出层插件 **/
function setDialog(obj){
	var dialogbox = $("#"+obj),
		doc = window,_$doc = $(doc.document.documentElement),
		mask = document.createElement("div"),
		cw = document.documentElement.clientWidth, 
		ch = document.documentElement.clientHeight, 
		est = document.documentElement.scrollTop;
	dialogbox.css({"top":(parseInt((ch) / 2) + est-150)+"px","left":(cw-dialogbox.width())/2+"px"}).show();
	mask.id = "mask"; mask.className = "mask";
	mask.style.height = document.body.clientHeight+"px";
	$("#mask")[0] ? $("#mask").show():document.body.appendChild(mask);
	
	var _fn = function(){};
	var dragEvent = {
		onstart: _fn,
		start: function(event)
		{
			var that = dragEvent;
			_$doc
			.bind( 'mousemove', that.move )
			.bind( 'mouseup', that.end );
			
			that._sClientX = event.clientX;
			that._sClientY = event.clientY;
			that.onstart( event.clientX, event.clientY );
			return false;
		},
		onmove: _fn,
		move: function(event)
		{
			var that = dragEvent;
			that.onmove(
				event.clientX - that._sClientX,
				event.clientY - that._sClientY
			);
			return false;
		},
		onend: _fn,
		end: function(event)
		{
			var that = dragEvent;
			_$doc
			.unbind('mousemove', that.move)
			.unbind('mouseup', that.end);
			that.onend(  event.clientX, event.clientY );
			return false;
		}	
	}
	_use = function(event)
	{
		var startWidth, startHeight, startLeft, startTop,
			wrap = dialogbox.get(0),
			_dragEvent = dragEvent,
			_doc = doc.document.documentElement,
		
		// 清除文本选择
			clsSelect = function( bool ){
				$( document )[ bool ? "unbind" : "bind" ]("selectstart", function(){
					return false;
				} )
				.css( "-user-select", bool ? "" : "none" );
				document.unselectable = bool ? "off" : "on";
			}
		// 对话框准备拖动
		_dragEvent.onstart = function( x, y )
		{
			startLeft = wrap.offsetLeft;
			startTop = wrap.offsetTop;
			
			_$doc.bind( 'dblclick', _dragEvent.end );
		};
		
		// 对话框拖动进行中
		_dragEvent.onmove = function( x, y )
		{
			var style = wrap.style,
				left = x + startLeft,
				top = y + startTop;

			style.left = Math.max( limit.minX, Math.min(limit.maxX,left) ) + 'px';
			style.top = Math.max( limit.minY, Math.min(limit.maxY,top) ) + 'px';
			clsSelect(false);
		};
		
		// 对话框拖动结束
		_dragEvent.onend = function( x, y )
		{
			_$doc.unbind('dblclick',_dragEvent.end);
			clsSelect(true);
		};
		limit =	(function(fixed)
		{
			var	ow = wrap.offsetWidth,
				// 向下拖动时不能将标题栏拖出可视区域
				oh = 49,
				ww = _$doc.find('body').width(),
				wh = 1100,
				dl = fixed ? 0 : _$doc.find('body').scrollLeft(),
				dt = fixed ? 0 : _$doc.find('body').scrollTop();
				// 坐标最大值限制(在可视区域内)	
				maxX = ww - ow + dl;
				maxY = wh - oh + dt;
			
			return {
				minX: dl,
				minY: dt,
				maxX: maxX,
				maxY: maxY
			};
		})(wrap.style.position === 'fixed');
		_dragEvent.start(event);
    }
	
	
	dialogbox.find(".d_head").bind("mousedown",function(event){
		_use(event);
	})
	dialogbox.find(".x_btn").bind("click",function(){
		dialogbox.hide();
		$("#mask").hide();
	})
}

//关闭弹出层
function closrDialog(){
	var dialog = $("div.dialog");
	dialog.hide();
	$("#mask").hide();
}