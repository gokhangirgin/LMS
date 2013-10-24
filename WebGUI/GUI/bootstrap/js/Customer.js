var CustomerInfo = function(){
	this.Id = ko.observable(1);
	this.FullName = ko.observable("Gokhan Girgin");
	this.Email = ko.observable("gokhangirgin@msn.com");
	this.Phone = ko.observable("5551234567");
	this.Address = ko.observable("asdasd")
};
var Order = function(){
	this.Id = ko.observable(1);
	this.Percentage = ko.observable(75);
};
var OrderList = function(){
	this.Orders = ko.observableArray();
};
var ItemModel = function(id,type,location,color,weight,washedc,material)
{
	this.Id = ko.observable(id);
	this.Type= ko.observable(type);
	this.Location = ko.observable(location);
	this.Color = ko.observable(color); //White
	this.Weight = ko.observable(weight);
	this.WashedCount = ko.observable(washedc);
	this.Material = ko.observable(material); //Cotton fln
	return this;
};
var ItemList = function()
{
	this.OrderId = ko.observable(0);
	this.Items = ko.observableArray();
};
var customer = new CustomerInfo();
var orders = new OrderList();
var items = new ItemList();
var userID = 1;
$(function(){

	var custLog = prompt("Customer Identification", "");
	userID = parseInt(custLog);
	var order = new Order();
	$.getJSON("/LmsService/Customer/GetOne?id=" + userID,function(data){
		customer.Id(data.id);
		customer.FullName(data.firstName + ' ' + data.lastName);
		customer.Email(data.email);
		customer.Phone(data.phone);
		customer.Address(data.address);
	});
	function ordersGet(){
	$.getJSON("/LmsService/OrderView/Perc?id=" + userID,function(data){
		if(data.length > 0)
		{
			if(orders.Orders().length > 0)
				orders.Orders([]);
			for(var i = 0; i < data.length;i++)
			{
				orders.Orders.push({Id : data[i].orderId,Percentage: data[i].percentage + '%'});
			}
		}
	});
	}
	ordersGet();
	$(document).on("click","a[data-type=details]",function(){
		var orderId = $(this).data("id");
		if(items.OrderId() != orderId){
			$.getJSON("/LmsService/OrderView/Items?id=" + orderId,function(data){
				var d;
				for(var i=0; i < data.length;i++){
					d = data[i];
					items.OrderId(orderId);
					items.Items.push(new ItemModel(d.id,d.type,d.location,d.color,d.weight,d.washedCount,d.material));
					$("#orderDetails").slideDown('slow');
					$("#orderDetails").focus();
				}
				
			});
		}
	});
	setInterval(ordersGet,2000);
	ko.applyBindings(items,$("#orderDetails")[0]);
	ko.applyBindings(orders,$("#orders")[0]);
	ko.applyBindings(customer,$("#customer")[0]);	
});