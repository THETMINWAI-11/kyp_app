
#customer
insert into Menu (id, Name, Icon, Url, Permission, SeqNo, IsParent, ParentMenuId) values (1, "Customer", "icon-users", "", "customer", 1, 1, null);
insert into Menu (id, Name, Icon, Url, Permission, SeqNo, IsParent, ParentMenuId) values (2, "New/Edit", "", "/customer/setup",  "customer-new", 2, 2, 1);
insert into Menu (id, Name, Icon, Url, Permission, SeqNo, IsParent, ParentMenuId) values (3, "Search", "", "/customer/search", "customer-search", 3, 2, 1);
