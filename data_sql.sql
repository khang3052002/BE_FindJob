select * from company
INSERT INTO company(name, image, introduce, location,employee_number, specialization, type_company, website)
values ('Google',
'https://news-cdn.softpedia.com/images/news2/the-new-google-logo-is-a-lesson-in-modern-design-490648-3.jpg',
'Google LLC is an American multinational technology company focusing on artificial intelligence, online advertising, search engine technology, cloud computing, computer software, quantum computing, e-commerce, and consumer electronics.',
'1600 Amphitheatre Parkway · Mountain View, California · U.S.',
120000,
'Search technology, Web computing, Software and Online advertising',
'Multinational company',
'https://about.google/'
),
(
	'Facebook',
    'https://th.bing.com/th/id/R.8e1e25f0151e2c319edb2aa01690e425?rik=3cw5V3mRdqONtg&riu=http%3a%2f%2f1000logos.net%2fwp-content%2fuploads%2f2016%2f11%2fFacebook-Logo-Meaning.jpg&ehk=FgCeZkf1OpID4t2mYFq2yRNkztI%2bRdJPC4oKPmv7KeY%3d&risl=&pid=ImgRaw&r=0',
    'Facebook can be accessed from devices with Internet connectivity, such as personal computers, tablets and smartphones. After registering, users can create a profile revealing information about themselves. They can post text, photos and multimedia which are shared with any other users who have agreed to be their "friend" or, with different privacy settings, publicly.',
    'Menlo Park, California, USA',
    1202000,
    'Social networking, online communication, digital advertising.',
    'Public technology company',
    'https://www.facebook.com/'
),
(
	'Amazon',
    'https://cdn.1min30.com/wp-content/uploads/2017/12/Embl%C3%A8me-Amazon.jpg',
    'Amazon.com, Inc. is an American multinational technology company focusing on e-commerce, cloud computing, online advertising, digital streaming, and artificial intelligence.',
    'Seattle, Washington, USA',
    1202000,
    'E-commerce, cloud computing, digital streaming, artificial intelligence and more',
    'Public technology and e-commerce company',
    'https://www.amazon.com/'
),
(
	'KMS Technology',
    'https://mentori.vn/upload/recruitment/sac1638011107.png',
    'Founded in 2009, KMS Technology delivers top-quality software and outsourcing services to clients worldwide. It focuses on software development, quality assurance, and consulting.',
    'Atlanta, Georgia, USA',
    45000,
    'Software development, quality assurance, IT consulting',
    'Private technology and outsourcing company',
    'https://kms-technology.com/'
)



// DATA JOB
SELECT * FROM job

INSERT INTO job(title,create_at, expire_at, salary, id_company, description, experience, facilities, location, position, qualification, required, specialization, type_work, type_work_place)
VALUES('Frontend Web Developer(Reactjs,Vuejs)',NOW(),'2024-05-23',1500,2,
'Sed ut perspiciatis unde omnis At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas . iste natus error',
'2 years',
'Medical, Meal Allowance, Regular Hours, Mondays-Fridays, Transport Allowance',
'Ho Chi Minh',
'Senior Designer',
'Bachelor’s Degree',
'Sed ut perspiciatis unde omnis iste natus error sit.\nNeque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur & adipisci velit.\nNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit.',
'Developer',
'Fulltime',
'Onsite'
),
('Mid-Level Backend Developer (Nodejs)',NOW(),'2024-05-23',1000,3,
'Sed ut perspiciatis unde omnis At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas . iste natus error',
'2 years',
'Medical, Meal Allowance, Regular Hours, Mondays-Fridays, Transport Allowance',
'Da Nang',
'Mid-level',
'Bachelor’s Degree',
'Sed ut perspiciatis unde omnis iste natus error sit.\nNeque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur & adipisci velit.\nNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit.',
'Developer',
'Fulltime',
'Onsite'
),
('Back End Developer (Golang, Python3)',NOW(),'2024-05-23',2000,3,
'Sed ut perspiciatis unde omnis At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas . iste natus error',
'3 years',
'Medical, Meal Allowance, Regular Hours, Mondays-Fridays, Transport Allowance',
'Ha Noi',
'Senior',
'Bachelor’s Degree',
'Sed ut perspiciatis unde omnis iste natus error sit.\nNeque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur & adipisci velit.\nNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit.',
'Developer',
'Fulltime',
'Remote'
),
('Front End Developer Intern (JavaScript, Vuejs/Nuxtjs)',NOW(),'2024-05-23',400,1,
'Sed ut perspiciatis unde omnis At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas . iste natus error',
'< 1 years',
'Medical, Meal Allowance, Regular Hours, Mondays-Fridays, Transport Allowance',
'District 5, Ho Chi Minh',
'Intern',
'Bachelor’s Degree',
'Sed ut perspiciatis unde omnis iste natus error sit.\nNeque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur & adipisci velit.\nNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit.',
'Developer',
'Fulltime',
'Onsite'
),
('Front-End Intern ( Reactjs )',NOW(),'2024-05-23',400,4,
'Sed ut perspiciatis unde omnis At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas . iste natus error',
'0 years',
'Medical, Meal Allowance, Regular Hours, Mondays-Fridays, Transport Allowance',
'District 5, Ho Chi Minh',
'Intern',
'Bachelor’s Degree',
'Sed ut perspiciatis unde omnis iste natus error sit.\nNeque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur & adipisci velit.\nNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit.',
'Developer',
'Fulltime',
'Onsite'
),
('Telesales (Telemarketing)',NOW(),'2024-05-23',400,4,
'Sed ut perspiciatis unde omnis At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas . iste natus error',
'0 years',
'Medical, Meal Allowance, Regular Hours, Mondays-Fridays, Transport Allowance',
'District 5, Ho Chi Minh',
'Official',
'Bachelor’s Degree',
'Sed ut perspiciatis unde omnis iste natus error sit.\nNeque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur & adipisci velit.\nNemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit.',
'Telesales',
'Fulltime',
'Onsite'
)



SELECT @@global.time_zone;
select now();
SELECT @@session.time_zone;
SET time_zone = 'Asia/Ho_Chi_Minh';
SELECT @@session.time_zone;
SET GLOBAL time_zone = 'Asia/Ho_Chi_Minh';