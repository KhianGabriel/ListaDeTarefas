//smtype.js: Biblioteca de recursos para manipulação de formulário

var TextCast = {
	maskCPF: function(txt) {
		return txt.substring(0, 3) + "." + txt.substring(3, 6) + "." +
			 txt.substring(6, 9) + "-" + txt.substring(9);
	},
};

const notCapitalize = ["dos", "do", "das", "da", "de", "e",
						"I", "II", "III", "IV", "V", "VI", "VII", "VII", "VIII", "IX", "X", "XXII",
						"XXIII", "PMVC", "CEI", "CE", "CMEI"];
						
const notCapitalizeUpper = ["I", "II", "III", "IV", "V", "VI", "VII", "VII", "VIII", "IX", "X", "XXII",
						"XXIII", "PMVC", "CEI", "CE", "CMEI"];

String.prototype.capitalize = function() {
		return this.replace(/(^\w{1})|(\s+\w{1})/g, letter => letter.toUpperCase());
};

String.prototype.isCapitalizable = function(text) {
	for (let i = 0; i < notCapitalize.length; i++)		
			if (text.toUpperCase() == notCapitalize[i].toUpperCase())
				return false;
	return true;
};

String.prototype.isCapitalizableUpper = function(text) {
	for (let i = 0; i < notCapitalizeUpper.length; i++)		
			if (text == notCapitalizeUpper[i])
				return true;
	return false;
};

String.prototype.capitalizeText = function() {
	const words = this.split(" ");
	let str = "";
	
	for (let i = 0; i < words.length; i++)		
		if (this.isCapitalizableUpper(words[i]))
			str += " " +  words[i].toUpperCase(); 
		else if (this.isCapitalizable(words[i]))
			str += " " + words[i][0].toUpperCase() + words[i].substring(1).toLowerCase();
				
		else	
			str += " " + words[i].toLowerCase();

	return str;
};
	
var SMUtil = {
	getIdArray: function($objList) {
		let idArray = [];
		for (i = 0; i < $objList.length; i++) {
			//console.log("Value: %s, Typeof: %s ", $objList[i], (typeof $objList[i]) );
			if (typeof $objList[i] === 'object' )
				idArray.push($objList[i].id.toString());
			else
				idArray.push( "" + $objList[i]);
		}
		return idArray;
	},
	
	setValues: function($form, data) {
		//console.log("PopulateForm, All form data: " + JSON.stringify(data));
		//console.log("$form: " + $form);

		$.each(data, function(key, value)   // all json fields ordered by name
		{
			//console.log("Data Element: " + key + " value: " + value );
			//var $ctrls = $form.find('[name='+key+']');  //all form elements for a name. Multiple checkboxes can have the same name, but different values
			var $ctrls = $form ? $form.find('[id=' + key + ']') : $('#' + key);

			//console.log("Number found elements: " + $ctrls.length );
			if ($ctrls.length) {
				//console.log("Data Element: " + key + " value: " + value );						
				if ($ctrls.is('select')) //special form types
				{
					/*$('option', $ctrls).each(function() {                    	
						if (this.value == value) {
							console.log("Value: %s, this.value: %s", value, this.value);
							this.selected = true;
						}                        
					});*/
					
					if ($ctrls.prop('multiple')) {
						//console.log("Key: %s, Value: %s, Typeof: %s ", key, value, (typeof value) );
						if (typeof value === 'object' /*&& !Array.isArray(value)*/)
							$ctrls.val(SMUtil.getIdArray(value)).trigger("change");
						else
							$ctrls.val(value).trigger("change");	
					}
					else {
						//console.log("Value=: %s, %s, %s - Name:=%s", (typeof value), value, $ctrls.val(), $ctrls.prop("name"));
						if (value && (typeof value === 'object')) {
							$ctrls.val(value.id).trigger("change");
							//console.log("Value=: %s, %s, %s - Name:=%s", (typeof value), value, $ctrls.val(), $ctrls.prop("name"));
						} else if ((typeof value === 'boolean')) {
							$ctrls.val(value.toString()).trigger("change");
							//console.log("Value=: %s, %s, %s - Name:=%s", (typeof value), value, $ctrls.val(), $ctrls.prop("name"));
						}
						else
							$ctrls.val(value).trigger("change");
							
						
						//console.log("Value: %s, Name: %s, JSON.value: %s", value, $ctrls.prop("name"), JSON.stringify(value));
					}				
				}
				else if ($ctrls.is('textarea')) {
					$ctrls.val(value);
				}
				else {
					switch ($ctrls.attr("type"))   //input type
					{
						case "text":
						case "date":						
						case "tel":
						case "email":					
						case "hidden":
							//console.log($ctrls.attr('data-date-format'));
							//console.log(value);
							let dateMask = $ctrls.attr('data-date-format');
							if(dateMask)
								$ctrls.val($sm.parseIsoDate(value).format(dateMask));
							else
								$ctrls.val(value);
							break;
						case "datetime-local":
							//console.log(value);
								$ctrls.val(value);
							break;
						case "radio":
							if ($ctrls.length >= 1) {
								//console.log("$ctrls.length: " + $ctrls.length + " value.length: " + value.length);
								$.each($ctrls, function(index) {  // every individual element
									var elemValue = $(this).attr("value");
									var elemValueInData = singleVal = value;
									if (elemValue === value) {
										$(this).prop('checked', true);
									}
									else {
										$(this).prop('checked', false);
									}
								});
							}
							break;
						case "checkbox":
							if ($ctrls.length > 1) {
								//console.log("$ctrls.length: " + $ctrls.length + " value.length: " + value.length);
								$.each($ctrls, function(index) // every individual element
								{
									var elemValue = $(this).attr("value");
									var elemValueInData = undefined;
									var singleVal;
									for (var i = 0; i < value.length; i++) {
										singleVal = value[i];
										console.log("singleVal : " + singleVal + " value[i][1]" + value[i][1]);
										if (singleVal === elemValue) { elemValueInData = singleVal };
									}
	
									if (elemValueInData) {
										//console.log("TRUE elemValue: " + elemValue + " value: " + value);
										$(this).prop('checked', true);
										//$(this).prop('value', true);
									}
									else {
										//console.log("FALSE elemValue: " + elemValue + " value: " + value);
										$(this).prop('checked', false);
										//$(this).prop('value', false);
									}
								});
							}
							else if ($ctrls.length == 1) {
								$ctrl = $ctrls;
								if (value) { $ctrl.prop('checked', true); }
								else {  $ctrl.prop('checked', false); }
	
							}
							break;
					}  //switch input type
				}
			}
		}) // all json fields
	},  // populate form

	loadValues: function($ctrl, data, text) {
		$ctrl.html("");
		if (text)
			$ctrl.append("<option selected disabled value=''>" + text + "</option>");

		//console.log("type:" + (typeof lotr));
		for (let i = 0; i < data.length; i++) {
			item = data[i];
			$ctrl.append("<option value='" + item.value + "'>" + item.text + "</option>");
		}
		//$("#cargo :nth-child(0)").prop("selected", "selected");
	},

	clearFeedback: function($form) {
		//console.log("Clear!");
		$("input, select, textarea", $form).each(function() {
			$(this).removeClass("is-invalid");
			$(this).removeClass("is-valid");
			//formElements.push($(this));
		});
		$(".invalid-feedback").remove();
		$(".sm-feedback").remove();
		//$( "#formCadastro" ).clear
	},

	showMessages: function($form, data) {
		erros = data.messages.erros;		
		SMUtil.clearFeedback($form);
		//$form.addClass("was-validated");
		for (i = 0; i < erros.length; i++) {			
			if (erros[i].typeMessage == 0) {
				$ctrl = $("#" + erros[i].fieldName);
				if ($ctrl[0]) {
					$ctrl.addClass("is-invalid");
					//console.log("Control: %s, %s", $ctrl.attr('name'), $ctrl.attr('class'));
					//console.log("Data: %s", $ctrl.attr('class').indexOf('select2'));
						
					if ( $ctrl.attr('class').indexOf('select2') != -1) {
						$ctrl.next().addClass("is-invalid");
						$ctrl.next().after('<div class="invalid-feedback">' + erros[i].message + '</div>');
					}
					else if ($("#" + erros[i].fieldName).next(".input-group-append").length) {
						$ctrl.next(".input-group-append").after('<div class="invalid-feedback">' + erros[i].message + '</div>');
					}
					else {
						//$("#" + list[i].fieldName ).nextAll().remove();
						$ctrl.after('<div class="invalid-feedback">' + erros[i].message + '</div>');
					}
				}
				else
					console.log("Campo do formulário: '%s' não encontrado, Msg: %s", erros[i].fieldName, erros[i].message);
			}
			else {
				$('.card-body', $form).append('<div class="form-group sm-feedback"><div class="invalid-feedback sm-invalid-feedback">' + erros[i].message + '</div></div>');
			}
		}
	},
	
	showDialog: function(msg, $element) {
		//console.log("teste!");
		if (!(msg))
			msg = "Operação realizado com sucesso!";		
		else if (msg == "save")
			msg = "Cadastro realizado com sucesso!"; 
		else if (msg == "update")
			msg = "Alteração realizada com sucesso!"; 
			
		if ($element)
			$element.append("<div id='sm_id_messages' class='hide sm_id_messages'></div>");
		else
			$("body").append("<div id='sm_id_messages' class='hide sm_id_messages'></div>");
		$("#sm_id_messages").show();
		$("#sm_id_messages").html(
			'<div id="sm_messages" class="col-sm-9 col-md-7 col-lg-7 hide mx-auto position-fixed p-3" aria-live="polite" aria-atomic="true" ' + 
			 'style="z-index: 9999; left: 50%; top: 50%; transform: translate(-50%, -50%); max-width: 580px;" >' +				
			'<div class="alert alert-success m-2" role="alert">' +
			'<button type="button" class="close ml-1" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
			msg +
			'</div>' +
			'</div>');

		//$(".alert").show();
		setTimeout(function() {
			$(".alert").fadeTo(500, 0).slideUp(500, function() {
				$(this).alert("close");		
				$("#sm_id_messages").hide();
				$("#sm_messages").remove();
				$(".sm_id_messages").remove();
				//console.log("setTimeout()");
			});
		}, 1200);
		//$("#sm_id_messages").remove();
	},

	load: function(id, url) {
		$.ajax({
			global: false, async: false, url: url, success: function(data) {
				$("#" + id).html(data);
			}
		});
	},
	
	post: function( url, data, response ) {
		$.ajax({ global: false, type: "POST", async: false, url: url, data: data, success: response });		
	}
};

$sm = SMUtil;

$sm.parseIsoDate = function(isoDateString) {
	let parts = isoDateString.split('T');
	
	let datePart = parts[0].split('-');
	let year = parseInt(datePart[0]);
	let monthIndex = parseInt(datePart[1]) - 1;
	let day = parseInt(datePart[2])
	
	let timePart = parts[1].split(':');
	let hours = parseInt(timePart[0]);
	let minutes = parseInt(timePart[1]);
	let sm = timePart[2].split('.');
	let seconds = parseInt(sm[0]);
	let milliseconds = parseInt(sm[1]);
	
	return new Date(year, monthIndex, day, hours, minutes, seconds, milliseconds);	
}

$sm.dateFormat = function () {
    var token = /d{1,4}|m{1,4}|yy(?:yy)?|([HhMsTt])\1?|[LloSZ]|"[^"]*"|'[^']*'/g,
        timezone = /\b(?:[PMCEA][SDP]T|(?:Pacific|Mountain|Central|Eastern|Atlantic) (?:Standard|Daylight|Prevailing) Time|(?:GMT|UTC)(?:[-+]\d{4})?)\b/g,
        timezoneClip = /[^-+\dA-Z]/g,
        pad = function (val, len) {
            val = String(val);
            len = len || 2;
            while (val.length < len) val = "0" + val;
            return val;
        };

    // Regexes and supporting functions are cached through closure
    return function (date, mask, utc) {
        var dF = $sm.dateFormat;

        // You can't provide utc if you skip other args (use the "UTC:" mask prefix)
        if (arguments.length == 1 && Object.prototype.toString.call(date) == "[object String]" && !/\d/.test(date)) {
            mask = date;
            date = undefined;
        }

        // Passing date through Date applies Date.parse, if necessary
        date = date ? new Date(date) : new Date;
        if (isNaN(date)) throw SyntaxError("invalid date");

        mask = String(dF.masks[mask] || mask || dF.masks["default"]);

        // Allow setting the utc argument via the mask
        if (mask.slice(0, 4) == "UTC:") {
            mask = mask.slice(4);
            utc = true;
        }

        var _ = utc ? "getUTC" : "get",
            d = date[_ + "Date"](),
            D = date[_ + "Day"](),
            m = date[_ + "Month"](),
            y = date[_ + "FullYear"](),
            H = date[_ + "Hours"](),
            M = date[_ + "Minutes"](),
            s = date[_ + "Seconds"](),
            L = date[_ + "Milliseconds"](),
            o = utc ? 0 : date.getTimezoneOffset(),
            flags = {
                d:    d,
                dd:   pad(d),
                ddd:  dF.i18n.dayNames[D],
                dddd: dF.i18n.dayNames[D + 7],
                m:    m + 1,
                mm:   pad(m + 1),
                mmm:  dF.i18n.monthNames[m],
                mmmm: dF.i18n.monthNames[m + 12],
                yy:   String(y).slice(2),
                yyyy: y,
                h:    H % 12 || 12,
                hh:   pad(H % 12 || 12),
                H:    H,
                HH:   pad(H),
                M:    M,
                MM:   pad(M),
                s:    s,
                ss:   pad(s),
                l:    pad(L, 3),
                L:    pad(L > 99 ? Math.round(L / 10) : L),
                t:    H < 12 ? "a"  : "p",
                tt:   H < 12 ? "am" : "pm",
                T:    H < 12 ? "A"  : "P",
                TT:   H < 12 ? "AM" : "PM",
                Z:    utc ? "UTC" : (String(date).match(timezone) || [""]).pop().replace(timezoneClip, ""),
                o:    (o > 0 ? "-" : "+") + pad(Math.floor(Math.abs(o) / 60) * 100 + Math.abs(o) % 60, 4),
                S:    ["th", "st", "nd", "rd"][d % 10 > 3 ? 0 : (d % 100 - d % 10 != 10) * d % 10]
            };

        return mask.replace(token, function ($0) {
            return $0 in flags ? flags[$0] : $0.slice(1, $0.length - 1);
        });
    };
}();

// Some common format strings
$sm.dateFormat.masks = {
    "default":      "ddd mmm dd yyyy HH:MM:ss",
    shortDate:      "m/d/yy",
    mediumDate:     "mmm d, yyyy",
    longDate:       "mmmm d, yyyy",
    fullDate:       "dddd, mmmm d, yyyy",
    shortTime:      "h:MM TT",
    mediumTime:     "h:MM:ss TT",
    longTime:       "h:MM:ss TT Z",
    isoDate:        "yyyy-mm-dd",
    isoTime:        "HH:MM:ss",
    isoDateTime:    "yyyy-mm-dd'T'HH:MM:ss",
    isoUtcDateTime: "UTC:yyyy-mm-dd'T'HH:MM:ss'Z'"
};

// Internationalization strings
$sm.dateFormat.i18n = {
    dayNames: [
        "Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab",
        "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"
    ],
    monthNames: [
        "Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez",
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    ]
};

// For convenience...
Date.prototype.format = function (mask, utc) {
    return $sm.dateFormat(this, mask, utc);
};


//Adicionando funcionalidade de limpar formulários ao JQuery, despensando o uso de jquery.form.
$.fn.clearForm = function() {
	this.each(function() {
		$('input,textarea,tel', this).clearFields();
	});
	$('select', this).each(function() {    
		$(this).prop('selectedIndex', 0);
	});
	
	SMUtil.clearFeedback();
};

$.fn.clearFeedback = function() {
	//if (this.is("form"))
		SMUtil.clearFeedback(this);
};

$.fn.clearFields = $.fn.clearInputs = function() {
	return this.each(function() {

		var t = this.type, tag = this.tagName.toLowerCase();
		//console.log("tag: " + t + ", tag:" + );
		if (t == 'hidden' || t == 'text' || t == 'password' || tag == 'textarea' || t == 'tel' || t == 'email') {
			this.value = '';

		}
		else if (t == 'checkbox' || t == 'radio') {
			this.checked = false;
		}
		else if (tag == 'select') {
			$(this).html('');
		}
	});
};

$.fn.setValues = function(data) {
	if (this.is("form"))
		SMUtil.setValues(this, data);
};

$.fn.loadValues = function(data, text) {
	if (this.is("select"))
		SMUtil.loadValues(this, data, text);
};

$.fn.showMessages = function(data) {
	if (this.is("form"))
		SMUtil.showMessages(this, data);
};

$.fn.changeVal = function(value) {	
	this.val(value).trigger("change");
};

$.extend(
{ 
	isMobile: function() {
	return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
	}
});

$.extend(
{
    redirectPost: function(location, args, target)
    {
        let form = '';
        $.each( args, function( key, value ) {
            form += '<input type="hidden" name="'+key+'" value="'+value+'">';
        });        
        m_target = target ? 'target="'+ target +'"' : '';
        $('<form action="'+location+'" method="POST" '+ m_target +'>'+form+'</form>').appendTo('body').submit();
    }
});

$.extend(
{
	removeAccents: function(data) {
	    if ( data.normalize ) {
	        // Use I18n API if avaiable to split characters and accents, then remove
	        // the accents wholesale. Note that we use the original data as well as
	        // the new to allow for searching of either form.
	        //return data +' '+ data
	        return data
	            .normalize('NFD')
	            .replace(/[\u0300-\u036f]/g, '');
	    }
    	return data;
	}

});

//Logout do sistema
$(document).ready(function() {
    $.ajaxSetup({   	   
        error: function( xhr, statusText, errorThrown )  {
		    //alert( "Error: " + xhr.status + ",  Status: " + statusText );
			//$.unblockUI;
			//$( ".blockUI" ).remove();
			
		    if ( xhr.status == 401 ) {
		        if ( confirm( 'Sua sessão expirou. Deseja ir para a página de login?' ) )
		            window.location = contextPath;
		    }
		    else if ( xhr.status == 0 ) {
		        alert( "Servidor indisponível, tente novamente mais tarde." );
		        //throw new Error( "Servidor indisponível, tente novamente mais tarde." );
		        return false;
		    }
		    else if ( xhr.status == 403 ) {
		        alert( "Permissão negada para acessar esse recurso!" );
		        return false;
		    }
		    else if ( xhr.status == 405 ) {
		    	$("#messages").html( "<div id= \"listErros\"><h2>Erros</h2><ul><li>Você não tem autorização para relizar essa operação!</li></ul></div>" );
		    	return false;
		    }
		    else {
		        alert( "Erro: " + xhr.status + ",  Status: " + statusText );
		        throw new Error( "Erro: " + xhr.status + ",  Status: " + statusText );
		        return false;
		    }
		}
	});
});


