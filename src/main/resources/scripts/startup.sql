--categories
INSERT INTO device_categories (icon, name)
VALUES
    ('pi pi-fw pi-video', 'Cameras'),
    ('pi pi-fw pi-shield', 'Security Systems'),
    ('pi pi-fw pi-home', 'Home Automation'),
    ('pi pi-fw pi-wifi', 'Network Devices'),
    ('pi pi-fw pi-sun', 'Environmental Sensors');

--devices
INSERT INTO devices (category_id, brand, description, image_url, model, name, compatibility, connectivity, power, warranty, type)
VALUES
    (1, 'Dahua', 'Smart IP camera', 'https://m.media-amazon.com/images/I/51gHV4Ad3BL.jpg', 'X1', 'IP Camera', 'LAN Network', 'UTP RJ45', 'PoE', '2 years', 'SENSOR'),
    (2, 'Bosch', 'Motion Sensor for security systems', 'https://m.media-amazon.com/images/I/41pWnkfRcJS._AC_UY218_.jpg', 'MSS-100', 'Motion Sensor', 'ZigBee', 'Wireless', 'Battery', '1 year', 'SENSOR'),
    (5, 'Siemens', 'Smart Thermostat', 'https://m.media-amazon.com/images/I/51srES+txRL._AC_SY879_.jpg', 'T-2024', 'Thermostat', 'Z-Wave', 'Wireless', 'Battery', '2 years', 'ACTUATOR'),
    (3, 'Philips', 'LED Smart Bulb', 'https://m.media-amazon.com/images/I/61CmXJTav5L.__AC_SX300_SY300_QL70_FMwebp_.jpg', 'Hue A19', 'Smart Bulb', 'Alexa', 'Wireless', 'AC', '2 years', 'ACTUATOR'),
    (5, 'Honeywell', 'Carbon Monoxide Detector', 'https://m.media-amazon.com/images/I/518mFXuReiL.__AC_SY300_SX300_QL70_FMwebp_.jpg', 'CO800', 'CO Detector', 'Wi-Fi', 'Wireless', 'Battery', '3 years', 'SENSOR'),
    (2, 'Samsung', 'Smart Door Lock', 'https://m.media-amazon.com/images/I/61pLKyMiNfL.__AC_SX300_SY300_QL70_FMwebp_.jpg', 'SDS-K9', 'Door Lock', 'Wi-Fi', 'Wireless', 'Battery', '2 years', 'ACTUATOR'),
    (3, 'Nest', 'Smart Smoke Detector', 'https://m.media-amazon.com/images/I/71wx88mgqhL.__AC_SX300_SY300_QL70_FMwebp_.jpg', 'Protect V2', 'Smoke Detector', 'Wi-Fi', 'Wireless', 'Battery', '5 years', 'SENSOR'),
    (1, 'Logitech', 'Smart Security Camera', 'https://m.media-amazon.com/images/I/6104drSUK6L.__AC_SX300_SY300_QL70_FMwebp_.jpg', 'Circle 2', 'Security Camera', 'Wi-Fi', 'Wireless', 'AC', '1 year', 'SENSOR'),
    (4, 'Schneider', 'Smart Plug', 'https://m.media-amazon.com/images/I/411tekLceiL._AC_SY450_.jpg', 'SP-101', 'Smart Plug', 'ZigBee', 'Wireless', 'AC', '3 years', 'ACTUATOR'),
    (2, 'Ring', 'Video Doorbell', 'https://m.media-amazon.com/images/I/71MvAWDYBBL.__AC_SX300_SY300_QL70_FMwebp_.jpg', 'VD2', 'Video Doorbell', 'Wi-Fi', 'Wireless', 'Battery', '1 year', 'SENSOR');

select * from devices;
