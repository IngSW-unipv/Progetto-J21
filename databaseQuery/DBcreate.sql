use progettoj21;

-- Cancellazione di eventuali table con lo stesso nome se presenti e loro ridefinizione
drop table if exists validatedticket;
drop table if exists soldregister;
drop table if exists ticketinspector;
drop table if exists verifiedticket;

-- Table soldregister, che memorizza gli ID dei titoli di viaggio acquistati
create table soldregister (
		TickID varchar(50),
        primary key (TickID));
        
-- Table verifiedticket, che memorizza l'ID del controllore che ha verificato la convalida del titolo di viaggio 
-- e l'ID del titolo di viaggio stesso
create table verifiedticket (
		TickID varchar(50),
        InspID varchar(50),
        primary key (TickID));  
        
-- Table validatedticket, che memorizza l'ID del titolo di viaggio convalidato e il suo orario di scadenza
create table validatedticket (
		TickID varchar(50),
        ExpTime varchar(50),
        primary key (TickID));
        
-- Table ticketinspector, che memorizza l'ID e la password per il login dei controllori. Sono inserite delle coppie
-- ID-password di prova per testare il funzionamento della procedura di login
create table ticketinspector (
				ID varchar(50),
                Password varchar(50),
                primary key (ID));
insert into ticketinspector (ID, Password)
values 
('00001', 'Stefano'), ('00002', 'Camilla'), ('00003', 'Lucia'), ('00004', 'Stino');