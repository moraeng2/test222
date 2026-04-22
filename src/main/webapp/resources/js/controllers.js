function addToCart() {
	//사용자가 어떤 버튼을 클릭할지는 모르는데
	// 클릭했을 때 얻을 수 있는 정보로 새로운 데이터 만들기 위함 : 동적

    document.addForm.submit();
    alert("도서가 장바구니에 추가되었습니다!");
}

function removeFromCart(action) {
    document.removeForm.action = action;
    document.removeForm.submit();
	alert("도서가 장바구니에 삭제되었습니다!");
    //window.location.reload();
}

function clearCart() {
    document.clearForm.submit();
    //window.location.reload();
}

function deleteConfirm(id){  
    if (confirm("삭제 합니다!!") == true) location.href ="./delete?id="+id;
    else  return;
}