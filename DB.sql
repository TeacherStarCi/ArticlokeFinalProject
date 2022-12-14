USE [Articloke]
GO
/****** Object:  Table [dbo].[Article]    Script Date: 19/11/2022 15:48:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Article](
	[ID] [varchar](20) NOT NULL,
	[link] [varchar](200) NOT NULL,
	[linkDemo] [varchar](200) NOT NULL,
	[publishedDate] [datetime] NOT NULL,
	[permission] [varchar](20) NOT NULL,
	[price] [float] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_Article_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Download]    Script Date: 19/11/2022 15:48:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Download](
	[ID] [varchar](20) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[downloadedDate] [datetime] NOT NULL,
 CONSTRAINT [PK_Download] PRIMARY KEY CLUSTERED 
(
	[ID] ASC,
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feedback]    Script Date: 19/11/2022 15:48:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feedback](
	[ID] [varchar](20) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[header] [varchar](100) NOT NULL,
	[content] [varchar](500) NOT NULL,
	[feedbackDate] [datetime] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_Feedback_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Notification]    Script Date: 19/11/2022 15:48:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Notification](
	[ID] [varchar](20) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[header] [varchar](50) NOT NULL,
	[content] [varchar](200) NOT NULL,
	[createdDate] [datetime] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_Nofitication] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Paper]    Script Date: 19/11/2022 15:48:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Paper](
	[ID] [varchar](20) NOT NULL,
	[title] [varchar](100) NOT NULL,
	[picture] [varchar](100) NOT NULL,
	[topic] [varchar](50) NOT NULL,
	[description] [varchar](500) NOT NULL,
	[content] [varchar](2000) NOT NULL,
	[createdDate] [datetime] NOT NULL,
	[modifiedDate] [datetime] NULL,
	[username] [varchar](50) NOT NULL,
	[publishedStatus] [varchar](50) NOT NULL,
	[advanced] [bit] NOT NULL,
	[submittedDate] [datetime] NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_Paper] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Reaction]    Script Date: 19/11/2022 15:48:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Reaction](
	[ID] [varchar](20) NOT NULL,
	[username] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Interaction] PRIMARY KEY CLUSTERED 
(
	[username] ASC,
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Topic]    Script Date: 19/11/2022 15:48:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Topic](
	[topic] [varchar](50) NOT NULL,
	[picture] [varchar](200) NOT NULL,
 CONSTRAINT [PK_Topic] PRIMARY KEY CLUSTERED 
(
	[topic] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Transaction]    Script Date: 19/11/2022 15:48:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Transaction](
	[ID] [varchar](20) NOT NULL,
	[usernameSender] [varchar](50) NOT NULL,
	[usernameReceiver] [varchar](50) NOT NULL,
	[amount] [float] NOT NULL,
	[sentDate] [datetime] NOT NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_Transaction_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User_]    Script Date: 19/11/2022 15:48:25 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User_](
	[username] [varchar](50) NOT NULL,
	[email] [varchar](100) NULL,
	[password] [varchar](50) NULL,
	[firstName] [varchar](50) NOT NULL,
	[lastName] [varchar](50) NOT NULL,
	[picture] [varchar](200) NULL,
	[balance] [float] NOT NULL,
	[role] [varchar](20) NOT NULL,
	[DOB] [date] NULL,
	[address] [varchar](100) NULL,
	[phoneNumber] [varchar](20) NULL,
	[status] [bit] NOT NULL,
	[organization] [varchar](50) NULL,
 CONSTRAINT [PK_User_] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR1', N'Asset/File/Official/AR1_Pub.pdf', N'Asset/File/Official/AR1_Demo.pdf', CAST(N'2022-11-11T04:06:32.923' AS DateTime), N'Public', 18.299999237060547, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR11', N'Asset/File/Official/NguyenVanTuCuong_CV (8).pdf', N'Asset/File/Official/NguyenVanTuCuong_CV (5).pdf', CAST(N'2022-11-15T08:06:31.027' AS DateTime), N'Public', 5, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR12', N'Asset/File/Official/Assignment_02_SalesManagement (4).pdf', N'Asset/File/Official/NguyenVanTuCuong_CV (2).pdf', CAST(N'2022-11-15T08:22:29.070' AS DateTime), N'Public', 43, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR13', N'Asset/File/Official/NguyenVanTuCuongCV.pdf', N'Asset/File/Official/Assignment_02_SalesManagement (4).pdf', CAST(N'2022-11-15T09:12:58.450' AS DateTime), N'Public', 183, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR2', N'Asset/File/Official/AR2 (1).pdf', N'Asset/File/Official/AR2 (1).pdf', CAST(N'2022-11-11T04:06:53.643' AS DateTime), N'Public', 20.299999237060547, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR3', N'Asset/File/Official/Assignment_02_SalesManagement (1).pdf', N'Asset/File/Official/2. ASP.NET Core project.pdf', CAST(N'2022-11-11T05:56:58.800' AS DateTime), N'Public', 50, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR5', N'Asset/File/Official/Asset_File_Official_2. ASP.NET Core project.pdf', N'Asset/File/Official/Asset_File_Official_AR1 (2).pdf', CAST(N'2022-11-11T07:10:13.360' AS DateTime), N'Public', 12, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR6', N'Asset/File/Official/AR2.pdf', N'Asset/File/Official/AR1_Demo.pdf', CAST(N'2022-11-11T07:10:22.747' AS DateTime), N'Public', 12, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR7', N'Asset/File/Official/Asset_File_Official_AR1 (3).pdf', N'Asset/File/Official/Asset_File_Official_AR1 (2).pdf', CAST(N'2022-11-11T07:10:30.487' AS DateTime), N'Public', 1, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR8', N'Asset/File/Official/Asset_File_Official_AR1 (2).pdf', N'Asset/File/Official/AR1.pdf', CAST(N'2022-11-11T07:10:44.203' AS DateTime), N'Public', 20.299999237060547, 1)
INSERT [dbo].[Article] ([ID], [link], [linkDemo], [publishedDate], [permission], [price], [status]) VALUES (N'AR9', N'Asset/File/Official/Assignment_02_SalesManagement.pdf', N'Asset/File/Official/2. ASP.NET Core project.pdf', CAST(N'2022-11-11T07:23:05.387' AS DateTime), N'Public', 15, 1)
GO
INSERT [dbo].[Download] ([ID], [username], [downloadedDate]) VALUES (N'AR1', N'starci', CAST(N'2022-11-11T04:15:04.873' AS DateTime))
INSERT [dbo].[Download] ([ID], [username], [downloadedDate]) VALUES (N'AR13', N'nhoc_dddADMIN', CAST(N'2022-11-15T09:15:24.497' AS DateTime))
INSERT [dbo].[Download] ([ID], [username], [downloadedDate]) VALUES (N'AR2', N'starci', CAST(N'2022-11-11T04:19:48.780' AS DateTime))
INSERT [dbo].[Download] ([ID], [username], [downloadedDate]) VALUES (N'AR3', N'nhoc_ddd', CAST(N'2022-11-11T05:26:26.107' AS DateTime))
INSERT [dbo].[Download] ([ID], [username], [downloadedDate]) VALUES (N'AR3', N'starci', CAST(N'2022-11-11T05:57:42.310' AS DateTime))
INSERT [dbo].[Download] ([ID], [username], [downloadedDate]) VALUES (N'AR4', N'starci', CAST(N'2022-11-11T05:40:49.183' AS DateTime))
INSERT [dbo].[Download] ([ID], [username], [downloadedDate]) VALUES (N'AR9', N'nhoc_dddADMIN', CAST(N'2022-11-11T07:24:34.303' AS DateTime))
GO
INSERT [dbo].[Feedback] ([ID], [username], [header], [content], [feedbackDate], [status]) VALUES (N'FB1', N'starci', N'Feedback Paper - AR4', N'Try Harder', CAST(N'2022-11-11T05:33:06.003' AS DateTime), 1)
INSERT [dbo].[Feedback] ([ID], [username], [header], [content], [feedbackDate], [status]) VALUES (N'FB2', N'116399129664329115432', N'Feedback Paper - AR10', N'bai viet khong hop le', CAST(N'2022-11-11T07:28:58.090' AS DateTime), 1)
INSERT [dbo].[Feedback] ([ID], [username], [header], [content], [feedbackDate], [status]) VALUES (N'FB3', N'starci', N'Feedback Paper - AR14', N'You need to edit Description in details', CAST(N'2022-11-15T09:18:15.620' AS DateTime), 1)
GO
INSERT [dbo].[Notification] ([ID], [username], [header], [content], [createdDate], [status]) VALUES (N'NT1', N'starci', N'Paper Reviewing Expired Submission - AR4', N'We apologise for not reviewing your pending paper on time. We have retrieved the paper back as Draft, and have compensated you 196$, our dear.', CAST(N'2022-11-11T05:31:54.767' AS DateTime), 1)
INSERT [dbo].[Notification] ([ID], [username], [header], [content], [createdDate], [status]) VALUES (N'NT2', N'starci', N'Paper Reviewing Rejection - AR4', N'We have rejected your pending paper due to some reasons, it has been retrieved back as Draft. Go to your repository to receive the feedback.', CAST(N'2022-11-11T05:33:06.003' AS DateTime), 1)
INSERT [dbo].[Notification] ([ID], [username], [header], [content], [createdDate], [status]) VALUES (N'NT3', N'116399129664329115432', N'Paper Reviewing Rejection - AR10', N'We have rejected your pending paper due to some reasons, it has been retrieved back as Draft. Go to your repository to receive the feedback.', CAST(N'2022-11-11T07:28:58.090' AS DateTime), 1)
INSERT [dbo].[Notification] ([ID], [username], [header], [content], [createdDate], [status]) VALUES (N'NT4', N'116399129664329115432', N'Paper Reviewing Expired Submission - AR10', N'We apologise for not reviewing your pending paper on time. We have retrieved the paper back as Draft, and have compensated you 196$, our dear.', CAST(N'2022-11-11T07:33:36.037' AS DateTime), 1)
INSERT [dbo].[Notification] ([ID], [username], [header], [content], [createdDate], [status]) VALUES (N'NT5', N'starci', N'Paper Reviewing Rejection - AR14', N'We have rejected your pending paper due to some reasons, it has been retrieved back as Draft. Go to your repository to receive the feedback.', CAST(N'2022-11-15T09:18:15.620' AS DateTime), 1)
GO
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR1', N'An Introduction To Butterfly Theorem', N'Asset/Image/Paper/Butterfly.jpg', N'Mathematics', N'Let M be the midpoint of a chord PQ of a circle, through which two other chords AB and CD are drawn; AD cuts PQ at X and BC cuts PQ at Y. Prove that M is also the midpoint of XY.', N'Let O be the center of the given circle. Since OM ? XY, in order to show that XM = MY, we have to prove that ?XOM = ?YOM.

Drop perpendiculars OK and ON from O onto AD and BC, respectively. Obviously, K is the midpoint of AD and N is the midpoint of BC. Further,

?DAB = ?DCB and ?ADC = ?ABC,

as angles subtending equal arcs. Triangles ADM and CBM are therefore similar, and AD/AM = BC/CM, or AK/AM = CN/CM. In other words, in triangles AKM and CNM two pairs of sides are proportional. Also, the angles between the corresponding sides are equal. We infer that the triangles AKM and CNM are similar. Hence, ?AKM = ?CNM.', CAST(N'2022-11-11T03:55:10.813' AS DateTime), CAST(N'2022-11-11T03:56:27.397' AS DateTime), N'nhoc_dddADMIN', N'Published', 1, CAST(N'2022-11-11T03:56:27.397' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR10', N're commonly raised as livestock for meat (beef or ', N'Asset/Image/Paper/CCCA.jpg', N'Animal', N're commonly raised as livestock for meat (beef or re commonly raised as livestock for meat (beef or re commonly raised as livestock for meat (beef or re commonly raised as livestock for meat (beef or ', N're commonly raised as livestock for meat (beef or re commonly raised as livestock for meat (beef or re commonly raised as livestock for meat (beef or re commonly raised as livestock for meat (beef or re commonly raised as livestock for meat (beef or re commonly raised as livestock for meat (beef or ', CAST(N'2022-11-11T07:27:52.300' AS DateTime), CAST(N'2022-11-01T07:29:14.810' AS DateTime), N'116399129664329115432', N'Draft', 0, NULL, 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR11', N'Dinh ly thang du trung hoa', N'Asset/Image/Paper/Die-3-Wense-3.png', N'Animal', N'Ch?ng minh. Ð?t $\gcd\left(m_1,\,m_2\right)=d$. N?u hai d?ng du d?ng th?i dó có m?t nghi?m, lúc dó \[x\equiv a_1,\,x\equiv a_2\pmod d\] và ta có \[d\mid a_1-a_2.\] ', N'Ch?ng minh. Ð?t $\gcd\left(m_1,\,m_2\right)=d$. N?u hai d?ng du d?ng th?i dó có m?t nghi?m, lúc dó \[x\equiv a_1,\,x\equiv a_2\pmod d\] và ta có \[d\mid a_1-a_2.\] N?u $d\mid a_1-a_2$, lúc dó nghi?m $x$ d? $x\equiv a_1\pmod m_1$ du?c cho b?i $x=a_1+m_1y$. Thay vào d?ng du th?c th? hai có $a_1+m_1y\equiv a_2\pmod m_2$. T? Ð?nh lý 6.1 d?ng du th?c mod $\dfrac{m_2}{d}$ có duy nh?t m?t nghi?m. T? dó nh?ng d?ng du d?ng th?i mod $m$ này có duy nh?t m?t nghi?m $x$.', CAST(N'2022-11-15T08:04:49.400' AS DateTime), CAST(N'2022-11-15T08:05:31.527' AS DateTime), N'starci', N'Published', 0, CAST(N'2022-11-15T08:05:31.527' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR12', N'sdfsdfdasd fdfsdf sdfs df sdf', N'Asset/Image/Paper/Die-3-Wense-3.png', N'Animal', N'Ð?nh lý Menelaus là m?t d?nh lý co b?n trong hình h?c tam giác, du?c phát bi?u nhu sau: Cho tam giác ABC. Các di?m D, E, F l?n lu?t n?m trên các du?ng th?ng BC, CA, AB. Khi dó D, E, F th?ng hàng khi và ch? khi', N'? dây ta nh?n th?y r?ng : N?u gi? s? DBDC.ECEA.FAFB=1 thì tùy vào s? di?m n?m trên c?nh , du?ng th?ng ch?a c?nh c?a tam giác màât có d?nh lý Ceva hay Menelaus.

N?u c? 3 di?m D,E,F n?m trên ba c?nh c?a tam giác ABC thì ta có d?nh lý Ceva.
N?u có 2 di?m n?m trên c?nh và 1 di?m n?m trên du?ng th?ng ch?a c?nh nhung n?m ngoài tam giác ABC thì ta có d?nh lý Menelaus.', CAST(N'2022-11-15T08:19:28.397' AS DateTime), CAST(N'2022-11-15T08:19:34.587' AS DateTime), N'starci', N'Published', 1, CAST(N'2022-11-15T08:19:34.587' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR13', N'An Introduction Fermat''s Last Theorem', N'Asset/Image/Paper/41-F3k3DzmL.jpg', N'Mathematics', N'In number theory, Fermat''s Last Theorem (sometimes called Fermat''s conjecture, especially in older texts) states that no three positive integers a, b, and c satisfy the equation an + bn = cn for any integer value of n greater than 2. The cases n = 1 and n = 2 have been known since antiquity to have infinitely many solutions.[1]', N'The proposition was first stated as a theorem by Pierre de Fermat around 1637 in the margin of a copy of Arithmetica. Fermat added that he had a proof that was too large to fit in the margin. Although other statements claimed by Fermat without proof were subsequently proven by others and credited as theorems of Fermat (for example, Fermat''s theorem on sums of two squares), Fermat''s Last Theorem resisted proof, leading to doubt that Fermat ever had a correct proof. Consequently the proposition became known as a conjecture rather than a theorem. After 358 years of effort by mathematicians, the first successful proof was released in 1994 by Andrew Wiles and formally published in 1995. It was described as a "stunning advance" in the citation for Wiles''s Abel Prize award in 2016.[2] It also proved much of the Taniyama-Shimura conjecture, subsequently known as the modularity theorem, and opened up entire new approaches to numerous other problems and mathematically powerful modularity lifting techniques.', CAST(N'2022-11-15T09:09:18.820' AS DateTime), CAST(N'2022-11-15T09:13:08.463' AS DateTime), N'starci', N'Pending', 1, CAST(N'2022-11-15T09:13:08.463' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR14', N'A Detail of Fermat''s Last Theorem', N'Asset/Image/Paper/41-F3k3DzmL.jpg', N'Mathematics', N'In number theory, Fermat''s Last Theorem (sometimes called Fermat''s conjecture, especially in older texts) states that no three positive integers a, b, and c satisfy the equation an + bn = cn for any integer value of n greater than 2. The cases n = 1 and n = 2 have been known since antiquity to have infinitely many solutions.[1]

The proposition was first state', N'In number theory, Fermat''s Last Theorem (sometimes called Fermat''s conjecture, especially in older texts) states that no three positive integers a, b, and c satisfy the equation an + bn = cn for any integer value of n greater than 2. The cases n = 1 and n = 2 have been known since antiquity to have infinitely many solutions.[1]

The proposition was first stated as a theorem by Pierre de Fermat around 1637 in the margin of a copy of Arithmetica. Fermat added that he had a proof that was too large to fit in the margin. Although other statements claimed by Fermat without proof were subsequently proven by others and credited as theorems of Fermat (for example, Fermat''s theorem on sums of two squares), Fermat''s Last Theorem resisted proof, leading to doubt that Fermat ever had a correct proof. Consequently the proposition became known as a conjecture rather than a theorem. After 358 years of effort by mathematicians, the first successful proof was released in 1994 by Andrew Wiles and formally published in 1995. It was described as a "stunning advance" in the citation for Wiles''s Abel Prize award in 2016.[2] It also proved much of the Taniyama-Shimura conjecture, subsequently', CAST(N'2022-11-15T09:17:13.173' AS DateTime), CAST(N'2022-11-15T09:17:21.280' AS DateTime), N'starci', N'Draft', 0, NULL, 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR2', N'An Introduction To Stolz-Cesaro Theorem', N'Asset/Image/Paper/slide-2.jpg', N'Mathematics', N'In mathematics, the Stolz–Cesaro theorem is a criterion for proving the convergence of a sequence. The theorem is named after mathematicians Otto Stolz and Ernesto Cesaro, who stated and proved it for the first time.

The Stolz–Cesaro theorem can be viewed as a generalization of the Cesaro mean, but also as a L''Hopital rule for sequences.', N'Let {\displaystyle (a_{n})_{n\geq 1}}(a_{n})_{n\geq 1} and {\displaystyle (b_{n})_{n\geq 1}}(b_{n})_{n\geq 1} be two sequences of real numbers. Assume that {\displaystyle (b_{n})_{n\geq 1}}(b_{n})_{n\geq 1} is a strictly monotone and divergent sequence (i.e. strictly increasing and approaching {\displaystyle +\infty }+\infty , or strictly decreasing and approaching {\displaystyle -\infty }-\infty ) and the following limit exists:

{\displaystyle \lim _{n\to \infty }{\frac {a_{n+1}-a_{n}}{b_{n+1}-b_{n}}}=l.\ }{\displaystyle \lim _{n\to \infty }{\frac {a_{n+1}-a_{n}}{b_{n+1}-b_{n}}}=l.\ }
Then, the limit

{\displaystyle \lim _{n\to \infty }{\frac {a_{n}}{b_{n}}}=l.\ }{\displaystyle \lim _{n\to \infty }{\frac {a_{n}}{b_{n}}}=l.\ }
//This Is LaTex, Please Use Latex Complier To See Result Clearly', CAST(N'2022-11-11T04:00:44.060' AS DateTime), CAST(N'2022-11-11T04:00:49.110' AS DateTime), N'nhoc_dddADMIN', N'Published', 0, CAST(N'2022-11-11T04:00:49.110' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR3', N'African Elephant Extinction Facts', N'Asset/Image/Paper/home9-give3.jpg', N'Animal', N'African elephants are the largest animals walking the Earth. Their herds wander through 37 countries in Africa. They are easily recognized by their trunk that is used for communication and handling objects. And their large ears allow them to radiate excess heat. ', N'African elephants are the largest animals walking the Earth. Their herds wander through 37 countries in Africa. They are easily recognized by their trunk that is used for communication and handling objects. And their large ears allow them to radiate excess heat. Upper incisor teeth develop into tusks in African elephants and grow throughout their lifetime. There are two species of African elephants—the savanna (or bush) elephant and the forest elephant. Savanna elephants are larger than forest elephants, and their tusks curve outwards. In addition to being smaller, forest elephants are darker and their tusks are straighter and point downward. There are also differences in the size and shape of the skull and skeleton between the two species.

Forest elephants are uniquely adapted to the dense forest habitat of the Congo Basin, but are in sharp decline due to poaching for the international ivory trade and habitat loss and fragmentation. It is estimated that probably one-quarter to one-third of the total African elephant population is made up of forest elephants.
//Reference Only', CAST(N'2022-11-11T05:21:08.240' AS DateTime), CAST(N'2022-11-11T05:21:49.843' AS DateTime), N'starci', N'Published', 1, CAST(N'2022-11-11T05:21:49.843' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR4', N'Research On Bacteria In The Mainstream Of Biology', N'Asset/Image/Paper/OIP (1).jpg', N'Bacteria', N'The study of the genetics, biochemistry, and physiology of bacteria during the last 40 years has provided the concepts and methods for the study of cells of all types at the molecular level. Although much is already known about the mechanisms bacteria use to regulate the expression of their genes, a great deal more remains to be discovered that will have relevance to both prokaryotic and eukaryotic cells.', N'The study of the genetics, biochemistry, and physiology of bacteria during the last 40 years has provided the concepts and methods for the study of cells of all types at the molecular level. Although much is already known about the mechanisms bacteria use to regulate the expression of their genes, a great deal more remains to be discovered that will have relevance to both prokaryotic and eukaryotic cells. Similarly, the study in bacteria of the transactions of DNA, of the synthesis and function of the cell membrane, of differentiation, and of the interaction with eukaryotic cells will undoubtedly produce results of general importance. The advantages of using bacteria for these studies include their simple noncompartmented structure, the accessibility of their genetic material, and the possibility of correlating the expression of a gene in the intact cell with its expression in a system composed of highly purified components. Finally, the comparative study of a wide variety of microorganisms may result in a better understanding of the evolution of prokaryotes and eukaryotes and lead to a comprehensive theory of cell biology.', CAST(N'2022-11-01T05:30:15.603' AS DateTime), CAST(N'2022-11-11T05:39:33.607' AS DateTime), N'starci', N'Published', 0, CAST(N'2022-11-11T05:39:33.607' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR5', N'C2H5OH And Its Application', N'Asset/Image/Paper/R.gif', N'Chemistry', N'C2H5OH or Ethanol can simply be called or termed alcohol and it is an organic chemical compound. The compound can also be represented as CH3-CH2-OH.

', N'C2H5OH Lewis Structure, Molecular Geometry, Hybridization, and Polarity
C2H5OH
C2H5OH or Ethanol can simply be called or termed alcohol and it is an organic chemical compound. The compound can also be represented as CH3-CH2-OH.

Ethanol is a colorless liquid with a distinct odor and a pungent taste. It has flammable properties; when burnt, the compound gives a blue color flame.

Here are some ways in which ethanol is prepared:

C2H4+H2SO4         ——->       CH3CH2SO4H

CH3CH2SO4H       +       H20        ———->       CH3CH2OH      +      H2SO4

C6H12O6       ——–>      2CH3CH2OH     +      2CO2

C12H22O11     +      H20      ——->       4CH3CH2OH       +      4CO2', CAST(N'2022-11-11T07:05:16.180' AS DateTime), CAST(N'2022-11-11T07:09:21.520' AS DateTime), N'starci', N'Published', 0, CAST(N'2022-11-11T07:09:21.520' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR6', N'Benzen C6H6 And Its Extension', N'Asset/Image/Paper/OIP (2).jpg', N'Chemistry', N'Benzene is an organic chemical compound with the molecular formula C6H6. The benzene molecule is composed of six carbon atoms joined in a planar ring with one hydrogen atom attached to each. Because it contains only carbon and hydrogen atoms, benzene is classed as a hydrocarbon.[15]', N'Benzene is a natural constituent of crude oil and is one of the elementary petrochemicals. Due to the cyclic continuous pi bonds between the carbon atoms, benzene is classed as an aromatic hydrocarbon. Benzene is a colorless and highly flammable liquid with a sweet smell, and is partially responsible for the aroma of gasoline. It is used primarily as a precursor to the manufacture of chemicals with more complex structure, such as ethylbenzene and cumene, of which billions of kilograms are produced annually. Although a major industrial chemical, benzene finds limited use in consumer items because of its toxicity.', CAST(N'2022-11-11T07:06:17.197' AS DateTime), CAST(N'2022-11-11T07:09:24.370' AS DateTime), N'starci', N'Published', 0, CAST(N'2022-11-11T07:09:24.370' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR7', N'Brocolli Good For Your Health', N'Asset/Image/Paper/CCCA.jpg', N'Plant', N'Broccoli (Brassica oleracea var. italica) is an edible green plant in the cabbage family (family Brassicaceae, genus Brassica) whose large flowering head, stalk and small associated leaves are eaten as a vegetable. Broccoli is classified in the Italica cultivar group of the species Brassica oleracea. ', N'Broccoli (Brassica oleracea var. italica) is an edible green plant in the cabbage family (family Brassicaceae, genus Brassica) whose large flowering head, stalk and small associated leaves are eaten as a vegetable. Broccoli is classified in the Italica cultivar group of the species Brassica oleracea. Broccoli has large flower heads, usually dark green, arranged in a tree-like structure branching out from a thick stalk which is usually light green. The mass of flower heads is surrounded by leaves. Broccoli resembles cauliflower, which is a different but closely related cultivar group of the same Brassica species.

It is eaten either raw or cooked. Broccoli is a particularly rich source of vitamin C and vitamin K. Contents of its characteristic sulfur-containing glucosinolate compounds, isothiocyanates and sulforaphane, are diminished by boiling but are better preserved by steaming, microwaving or stir-frying.[3]', CAST(N'2022-11-11T07:07:32.170' AS DateTime), CAST(N'2022-11-11T07:09:27.347' AS DateTime), N'starci', N'Published', 0, CAST(N'2022-11-11T07:09:27.347' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR8', N'COVID 19 And Its Effect And Effect', N'Asset/Image/Paper/CONA.jpg', N'COVID-19', N'Coronavirus disease 2019 (COVID-19) is a contagious disease caused by a virus, the severe acute respiratory syndrome coronavirus 2 (SARS-CoV-2). The first known case was identified in Wuhan, China, in December 2019.[7] The disease quickly spread worldwide, resulting in the COVID-19 pandemic.', N'Symptoms of COVID-19 are variable, but often include fever,[8] cough, headache,[9] fatigue, breathing difficulties, loss of smell, and loss of taste.[10][11][12] Symptoms may begin one to fourteen days after exposure to the virus. At least a third of people who are infected do not develop noticeable symptoms.[13] Of those people who develop symptoms noticeable enough to be classed as patients, most (81%) develop mild to moderate symptoms (up to mild pneumonia), while 14% develop severe symptoms (dyspnoea, hypoxia, or more than 50% lung involvement on imaging), and 5% develop critical symptoms (respiratory failure, shock, or multiorgan dysfunction).[14] Older people are at a higher risk of developing severe symptoms. Some people continue to experience a range of effects (long COVID) for months after recovery, and damage to organs has been observed.[15] Multi-year studies are underway to further investigate the long-term effects of the disease.[15]', CAST(N'2022-11-11T07:09:12.350' AS DateTime), CAST(N'2022-11-11T07:09:30.520' AS DateTime), N'starci', N'Published', 0, CAST(N'2022-11-11T07:09:30.520' AS DateTime), 1)
INSERT [dbo].[Paper] ([ID], [title], [picture], [topic], [description], [content], [createdDate], [modifiedDate], [username], [publishedStatus], [advanced], [submittedDate], [status]) VALUES (N'AR9', N'A fresh cow is a dairy term for a cow or first-calf heifer', N'Asset/Image/Paper/OIP (3).jpg', N'Animal', N'Cattle are commonly raised as livestock for meat (beef or veal, see beef cattle), for milk (see dairy cattle), and for hides, which are used to make leather. They are used as riding animals and draft animals (oxen or bullocks, which pull carts, plows and other implements). 
//REVIEWEW', N'Different types of cattle are common to different geographic areas. Taurine cattle are found primarily in Europe and temperate areas of Asia, the Americas, and Australia. Zebus (also called indicine cattle) are found primarily in India and tropical areas of Asia, America, and Australia. Sanga cattle are found primarily in sub-Saharan Africa. These types (which are sometimes classified as separate species or subspecies) are further divided into over 1000 recognized breeds.

Around 10,500 years ago, taurine cattle were domesticated from as few as 80 wild aurochs progenitors in central Anatolia, the Levant and Western Iran.[1] A separate domestication event occurred in the Indian subcontinent, which gave rise to zebu. According to the Food and Agriculture Organization (FAO), there are approximately 1.5 billion cattle in the world as of 2018.[2] Cattle are the main source of greenhouse gas emissions from livestock, and are responsible for around 10% of global greenhouse gas emissions.[3][4] In 2009, cattle became one of the first livestock animals to have a fully mapped genome.[5]
//EDITED', CAST(N'2022-11-11T07:17:44.763' AS DateTime), CAST(N'2022-11-01T07:18:25.473' AS DateTime), N'starci', N'Published', 1, CAST(N'2022-11-11T07:18:25.473' AS DateTime), 1)
GO
INSERT [dbo].[Reaction] ([ID], [username]) VALUES (N'AR1', N'nhoc_dddADMIN')
INSERT [dbo].[Reaction] ([ID], [username]) VALUES (N'AR13', N'nhoc_dddADMIN')
INSERT [dbo].[Reaction] ([ID], [username]) VALUES (N'AR11', N'starci')
INSERT [dbo].[Reaction] ([ID], [username]) VALUES (N'AR7', N'starci')
INSERT [dbo].[Reaction] ([ID], [username]) VALUES (N'AR9', N'starci')
GO
INSERT [dbo].[Topic] ([topic], [picture]) VALUES (N'Animal', N'fa-paw')
INSERT [dbo].[Topic] ([topic], [picture]) VALUES (N'Bacteria', N'fa-bacterium')
INSERT [dbo].[Topic] ([topic], [picture]) VALUES (N'Chemistry', N'fa-vial')
INSERT [dbo].[Topic] ([topic], [picture]) VALUES (N'COVID-19', N'fa-virus-covid')
INSERT [dbo].[Topic] ([topic], [picture]) VALUES (N'Environment', N'fa-earth-americas')
INSERT [dbo].[Topic] ([topic], [picture]) VALUES (N'Mathematics', N'fa-square-root-variable')
INSERT [dbo].[Topic] ([topic], [picture]) VALUES (N'Plant', N'fa-seedling')
GO
INSERT [dbo].[Transaction] ([ID], [usernameSender], [usernameReceiver], [amount], [sentDate], [status]) VALUES (N'TS1', N'starci', N'nhoc_dddADMIN', 18.299999237060547, CAST(N'2022-11-11T04:15:04.953' AS DateTime), 1)
INSERT [dbo].[Transaction] ([ID], [usernameSender], [usernameReceiver], [amount], [sentDate], [status]) VALUES (N'TS2', N'starci', N'nhoc_dddADMIN', 20.299999237060547, CAST(N'2022-11-11T04:19:48.857' AS DateTime), 1)
INSERT [dbo].[Transaction] ([ID], [usernameSender], [usernameReceiver], [amount], [sentDate], [status]) VALUES (N'TS3', N'nhoc_ddd', N'starci', 15, CAST(N'2022-11-11T05:26:26.187' AS DateTime), 1)
INSERT [dbo].[Transaction] ([ID], [usernameSender], [usernameReceiver], [amount], [sentDate], [status]) VALUES (N'TS4', N'starci', N'starci', 0, CAST(N'2022-11-11T05:40:49.257' AS DateTime), 1)
INSERT [dbo].[Transaction] ([ID], [usernameSender], [usernameReceiver], [amount], [sentDate], [status]) VALUES (N'TS5', N'starci', N'starci', 0, CAST(N'2022-11-11T05:57:42.387' AS DateTime), 1)
INSERT [dbo].[Transaction] ([ID], [usernameSender], [usernameReceiver], [amount], [sentDate], [status]) VALUES (N'TS6', N'nhoc_dddADMIN', N'starci', 15, CAST(N'2022-11-11T07:24:34.460' AS DateTime), 1)
INSERT [dbo].[Transaction] ([ID], [usernameSender], [usernameReceiver], [amount], [sentDate], [status]) VALUES (N'TS7', N'nhoc_dddADMIN', N'starci', 183, CAST(N'2022-11-15T09:15:24.683' AS DateTime), 1)
GO
INSERT [dbo].[User_] ([username], [email], [password], [firstName], [lastName], [picture], [balance], [role], [DOB], [address], [phoneNumber], [status], [organization]) VALUES (N'116399129664329115432', N'cuongnvtse160875@fpt.edu.vn', NULL, N'Nguyen Van Tu Cuong', N'(K16_HCM)', N'https://lh3.googleusercontent.com/a/ALm5wu198tcImplLoSIf8ND_u8tH9hGrAeo7hXtO8Eq-5A=s96-c', 196, N'User', NULL, NULL, NULL, 1, NULL)
INSERT [dbo].[User_] ([username], [email], [password], [firstName], [lastName], [picture], [balance], [role], [DOB], [address], [phoneNumber], [status], [organization]) VALUES (N'nhoc_ddd', NULL, N'123456', N'Nguyen Van Tu', N'Cuong', N'', 985, N'User', NULL, NULL, NULL, 1, NULL)
INSERT [dbo].[User_] ([username], [email], [password], [firstName], [lastName], [picture], [balance], [role], [DOB], [address], [phoneNumber], [status], [organization]) VALUES (N'nhoc_dddADMIN', NULL, N'123456', N'Nguyen Van Tu', N'Cuong', N'Asset/Image/User/StarCi.jpg', 809.89996337890625, N'Admin', CAST(N'2002-03-18' AS Date), N'53 Tan Lap 1', N'0828678897', 1, N'FPT University')
INSERT [dbo].[User_] ([username], [email], [password], [firstName], [lastName], [picture], [balance], [role], [DOB], [address], [phoneNumber], [status], [organization]) VALUES (N'nhoc_dddEDITOR', NULL, N'123456', N'Nguyen Van Tu', N'Cuong', NULL, 1000, N'Editor', NULL, NULL, NULL, 1, NULL)
INSERT [dbo].[User_] ([username], [email], [password], [firstName], [lastName], [picture], [balance], [role], [DOB], [address], [phoneNumber], [status], [organization]) VALUES (N'nhoc_dddPUBLISHER', NULL, N'123456', N'Nguyen Van Tu', N'Cuong', NULL, 1000, N'Publisher', NULL, NULL, NULL, 1, NULL)
INSERT [dbo].[User_] ([username], [email], [password], [firstName], [lastName], [picture], [balance], [role], [DOB], [address], [phoneNumber], [status], [organization]) VALUES (N'starci', NULL, N'123456', N'Trinh Gia', N'Bao Ngoc', N'Asset/Image/User/StarCi.jpg', 1558.099853515625, N'Editor', CAST(N'2002-03-18' AS Date), N'53 Tan Lap 1', N'0828678897', 1, N'FPT University')
INSERT [dbo].[User_] ([username], [email], [password], [firstName], [lastName], [picture], [balance], [role], [DOB], [address], [phoneNumber], [status], [organization]) VALUES (N'starci123', NULL, N'123456', N'Nguyen Van1', N'CUONG', NULL, 0, N'Editor', NULL, NULL, NULL, 1, NULL)
GO
ALTER TABLE [dbo].[Article]  WITH CHECK ADD  CONSTRAINT [FK_Article_Paper] FOREIGN KEY([ID])
REFERENCES [dbo].[Paper] ([ID])
GO
ALTER TABLE [dbo].[Article] CHECK CONSTRAINT [FK_Article_Paper]
GO
ALTER TABLE [dbo].[Download]  WITH CHECK ADD  CONSTRAINT [FK_Download_Paper] FOREIGN KEY([ID])
REFERENCES [dbo].[Paper] ([ID])
GO
ALTER TABLE [dbo].[Download] CHECK CONSTRAINT [FK_Download_Paper]
GO
ALTER TABLE [dbo].[Download]  WITH CHECK ADD  CONSTRAINT [FK_Download_User_] FOREIGN KEY([username])
REFERENCES [dbo].[User_] ([username])
GO
ALTER TABLE [dbo].[Download] CHECK CONSTRAINT [FK_Download_User_]
GO
ALTER TABLE [dbo].[Feedback]  WITH CHECK ADD  CONSTRAINT [FK_Feedback_User_] FOREIGN KEY([username])
REFERENCES [dbo].[User_] ([username])
GO
ALTER TABLE [dbo].[Feedback] CHECK CONSTRAINT [FK_Feedback_User_]
GO
ALTER TABLE [dbo].[Notification]  WITH CHECK ADD  CONSTRAINT [FK_Nofitication_User_] FOREIGN KEY([username])
REFERENCES [dbo].[User_] ([username])
GO
ALTER TABLE [dbo].[Notification] CHECK CONSTRAINT [FK_Nofitication_User_]
GO
ALTER TABLE [dbo].[Paper]  WITH CHECK ADD  CONSTRAINT [FK_Paper_Topic] FOREIGN KEY([topic])
REFERENCES [dbo].[Topic] ([topic])
GO
ALTER TABLE [dbo].[Paper] CHECK CONSTRAINT [FK_Paper_Topic]
GO
ALTER TABLE [dbo].[Reaction]  WITH CHECK ADD  CONSTRAINT [FK_Reaction_Paper] FOREIGN KEY([ID])
REFERENCES [dbo].[Paper] ([ID])
GO
ALTER TABLE [dbo].[Reaction] CHECK CONSTRAINT [FK_Reaction_Paper]
GO
ALTER TABLE [dbo].[Reaction]  WITH CHECK ADD  CONSTRAINT [FK_Reaction_User_] FOREIGN KEY([username])
REFERENCES [dbo].[User_] ([username])
GO
ALTER TABLE [dbo].[Reaction] CHECK CONSTRAINT [FK_Reaction_User_]
GO
ALTER TABLE [dbo].[Transaction]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_User_] FOREIGN KEY([usernameSender])
REFERENCES [dbo].[User_] ([username])
GO
ALTER TABLE [dbo].[Transaction] CHECK CONSTRAINT [FK_Transaction_User_]
GO
ALTER TABLE [dbo].[Transaction]  WITH CHECK ADD  CONSTRAINT [FK_Transaction_User_1] FOREIGN KEY([usernameReceiver])
REFERENCES [dbo].[User_] ([username])
GO
ALTER TABLE [dbo].[Transaction] CHECK CONSTRAINT [FK_Transaction_User_1]
GO
