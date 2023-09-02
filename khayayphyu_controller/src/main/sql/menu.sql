
#customer
insert into menu (id, Name, Icon, Url, Permission, SeqNo, IsParent, ParentMenuId) values (1, "Customer", "icon-users", "", "customer", 1, 1, null);
insert into menu (id, Name, Icon, Url, Permission, SeqNo, IsParent, ParentMenuId) values (2, "New/Edit", "", "/customer/setup",  "customer-new", 2, 2, 1);
insert into menu (id, Name, Icon, Url, Permission, SeqNo, IsParent, ParentMenuId) values (3, "Search", "", "/customer/search", "customer-search", 3, 2, 1);

#Product
insert into menu (id, Name, Icon, Url, Permission, SeqNo, IsParent, ParentMenuId) values (4, "Product", "icon-users", "", "product", 4, 1, null);
insert into menu (id, Name, Icon, Url, Permission, SeqNo, IsParent, ParentMenuId) values (5, "New/Edit", "", "/product/setup",  "product-new", 5, 2, 4);
insert into menu (id, Name, Icon, Url, Permission, SeqNo, IsParent, ParentMenuId) values (6, "Search", "", "/product/search",  "product-search", 6, 2, 4);
