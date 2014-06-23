	// 아스키코드 범위를 이용한 한글 입력 검사
	function isName( value ) {
		if ( value.length > 0 ) {
			for ( var i=0; i<value.length; ++i ) {
				if ( value.charCodeAt(i) < 128 ) {	// 웬만한 특수문자 숫자 영문자는 여기 다 포함된다.
					return false;
				}
			}
			
			return true;
		}

		return false;		// 내용없음
	}
	
	// 정규표현식을 이용한 이메일 유효성 검사.
	function isEmail(value) {
		var regExp = /^[_a-zA-Z0-9\.]+([-+.][_a-zA-Z0-9]+)*@[_a-zA-Z0-9]+([-.][_a-zA-Z0-9]+)*\.[_a-zA-Z0-9]+([-.][_a-zA-Z0-9]+)*$/;
		if (regExp.test(value)) {
			return true;
		}
		return false;
	}
	
	// 정규표현식을 이용한 전화번호 유효성 검사.
	function checkPhone( phone ) {
		var regExp = /^[0]\d{1,2}\-\d{3,4}\-\d{4}$/;

		if ( phone.length > 0 ) {
			if ( regExp.test( phone ) ) {
				return true;
			}
		}
		
		return false;
	}
	
	// 정규표현식을 이용한 전화번호 유효성 검사.
	function isPhoneNoHyphen( phone ) {
		var regExp = /^[0]\d{1,2}\d{3,4}\d{4}$/;

		if ( phone.length > 0 ) {
			if ( regExp.test( phone ) ) {
				return true;
			}
		}
		
		return false;
	}

	// 전화번호 유효성 검사 (3개로 나눠진 버전)
	function checkPhone2( phone1, phone2, phone3 ) {
		var regExp1 = /^[0]\d{1,2}$/;
		var regExp2 = /^\d{3,4}$/;
		var regExp3 = /^\d{4}$/;

		if ( phone1.length > 0 && phone2.length > 0 && phone3.length > 0 ) {
			if ( regExp1.test( phone1 ) && regExp2.test( phone2 ) && regExp3.test( phone3 ) ) {
				return true;
			}
		}

		return false;
	}
	
	/*
		전화번호 유효성 검사
		phone1 - 필수인자
		phone2, phone3 - 선택인자(전화번호가 3개의 부분으로 나눠진 경우)
	*/
	function isPhone( phone1, phone2, phone3 ) {
		if ( phone1 && phone2 && phone3 ) {
			return checkPhone2( phone1, phone2, phone3 );
		}
		else if ( phone1 && !phone2 && !phone3 ) {
			return checkPhone( phone1 );
		}
		
		return false;	// 필수인자인 phone1 이 빈값인경우
	}

	// radio, checkbox 의 체크 유무 확인
	function isChecked( elements ) {
		var isChecked = false;

		for ( var i=0; i<elements.length; ++i ) {
			if ( elements[i].checked ) {
				isChecked = true;
				break;
			}
		}

		return isChecked;
	}
	
	// checkbox 배열에서 value 에 해당하는 항목의 checked 여부 확인
	function isCheckedByValue( checkbox, value ) {
		var isCheck = false;
		
		if ( checkbox && value ) {
			for ( var i=0; i<checkbox.length; ++i ) {
				if ( checkbox[i].value == value ) {
					if ( checkbox[i].checked ) {
						isCheck = true;
						break;
					}
				}
			}
		}

		return isCheck;
	}
	
	/*
	 *	pass1, pass2 - 문자열
	 *	length - 숫자 (비밀번호의 제한길이, 선택적 인자)
	 */
	function checkPassword( pass1, pass2, length ) {
		if ( !pass1 || ( pass1 != pass2 ) ) {
			return false;
		}
		if ( length && pass1.length < length ) {
			return false;
		}
		
		return true;
	}
	
	/*
	 *	시작일과 종료일 날짜 범위를 확인하는 함수
	 *	startDate 와 endDate 는 모두 javascript 의 Date 객체
	 */
	function verifyDateRange( startDate, endDate ) {
		if ( startDate.getTime() > endDate.getTime() ) {
			return false;
		}
		
		return true;
	}
	
	/*
	 *	숫자인지 검사하는 정규표현식
	 */
	function isNumber( value ) {
		var regExp = /^[0-9]+$/;
		
		if ( regExp.test( value ) ) {
			return true;
		}
		
		return false;
	}
	
	/*
	 * 우편번호인지 검사하는 함수
	 */
	function isZipCode(zipCode1, zipCode2) {
		if ( !isNumber(zipCode1) || !isNumber(zipCode2) ) {
			return false;
		}
		else if ( zipCode1.length != 3 || zipCode2.length != 3) {
			return false;
		}
		
		return true;
	}
	
	/*
	 *	날짜형식인지 검사하는 함수 
	 */
	function isValidDate(s) { 

	    var pt = /^\d{8}$/;
	    if (!pt.test(s)) return false;
	 
	    var y = parseInt(s.substr(0,4), 10); 
	    var m = parseInt(s.substr(4,2), 10) - 1; 
	    var d = parseInt(s.substr(6,2), 10); 
	 
	    var dt = new Date(y, m, d);
	    
	    if (dt.getFullYear() == y && dt.getMonth() == m && dt.getDate() == d) { 
	        return true; 
	    } 
	    else { 
	        return false; 
	    } 
	}
	
	/*
	 *	숫자가 아닌 문자를 제거해준다
	 */
	function removeNotTheNumbers( value ) {
		return value.replace( /[^0-9]/g, "" );
	}
	
	/*
	 *	3자리마다 ,를 추가하는 함수
	 *	element - input type="text"
	 */
	function makeCurrency( element ) {
		var start = 0;
		var end = 0;
		var result = "";
		var strMoney = removeNotTheNumbers( element.value );

		if ( strMoney.length > 3 ) {
			end = strMoney.length % 3;
			
			if (end == 0) {
				end += 3;
			}
			// 앞부분
			result = strMoney.substring( start, end ) + ",";
			start += end;
			end += 3;
			// 가운데
			while ( end != strMoney.length ) {
				result += strMoney.substring( start, end ) + ",";
				start += 3;
				end += 3;
			}
			// 마지막
			result += strMoney.substring( start, end );
			element.value = result;
			return;
		}
		else {
			element.value = strMoney;
			return;
		}
	}
	
	/**
	 * 만 나이 계산
	 * @param jumin1	주민번호 앞자리
	 * @param jumin2	주민번호 뒷자리
	 * @return			만나이
	 */
	function getFullAge(jumin1, jumin2) {
		
		var fullAge = 0;
		
		if (jumin1 && jumin2) {
			
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();

			var chkAge = "";
			// 1900 년대 태어난 사람들
			if (jumin2.substring(0, 1) == "1" || jumin2.substring(0, 1) == "2") {
				chkAge = "19" + jumin1.substring(0, 2);
			}
			else { // 1900년대가 아닌 사람들(이전은 없을테니.. 2000이후 태어난 사람들)
				chkAge = "20" + jumin1.substring(0, 2);
			}

			fullAge = year - parseInt(chkAge, 10);
			
			if (month < parseInt(jumin1.substring(2, 4), 10)) {
				fullAge = fullAge -1;
			}
			else if (month == parseInt(jumin1.substring(2, 4), 10)) {
				if (day < parseInt(jumin1.substring(4), 10)) {
					fullAge = fullAge -1 ;
				}
			}
		}
		
		return fullAge;
	}
	
	
	//입력 자릿수만큼 입력을 다 받게되면 자동으로 다음 입력엘리먼트로 focus 이동
	function autoTab(currId, nextId, maxLength) {

		if ( window.event.keyCode == 9 )		// 입력된 키가 tab key 라면 그냥 종료
			return;
		
		if ( currId && nextId && maxLength ) {
		
			var curr = document.getElementById(currId);
			var next = document.getElementById(nextId);
	
			if ( curr && curr.value && next ) {
				if ( curr.value.length >= maxLength ) {
					next.focus();
				}
			}
		}
	}
	
	/**
	 * element의 포커스 이동
	 * 
	 * @param currId	현재 포커스의 element ID
	 * @param nextId	이동 해야할 포커스의 element ID
	 * @param length	현재 input element의 value.length 와 비교할 값
	 * @return			없음
	 */
	function moveNextInput(currId, nextId, length) {
		
		if ( currId && nextId ) {
		
			var curr = document.getElementById(currId);
			var next = document.getElementById(nextId);
			
			if ( length ) {
				if ( curr.value && curr.value.length >= length ) {
					next.focus();
				}
			}
			else {
				next.focus();
			}
		}
	}
	
	function isIPAddress(cpip) {

	    if(isNaN(cpip) == false){
		    alert("정확한 아이피를 입력하십시오");
		    return false;
	    }
	    dot_cnt = cpip.split(".");

	    if(dot_cnt.length < 4){
	       alert("사용하는 아이피를 정확하게 입력하십시오");
	       return false;
	    }

	    for(var i=0; i<dot_cnt.length; i++){
	       if(isNaN(dot_cnt[i])==true){
	          alert("아이피는 숫자로 입력하셔야 합니다.");
	          return false;
	       }

	       if((parseInt(dot_cnt[i]) > 256) || (parseInt(dot_cnt[i]) < 0)){
	          alert("아이피는 1~255사이의 숫자를 입력하십시오");
	          return false;
	       }
	    }

	    return true;
	}

