package com.newsletter.cstoday.mail.application;

public class WelcomeMail {

    public static String welcomeMailContent;

    static {
        welcomeMailContent =
                "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n" +
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\r\n" +
                        "<head>\r\n" +
                        "<!--[if gte mso 9]>\r\n" +
                        "<xml>\r\n" +
                        "  <o:OfficeDocumentSettings>\r\n" +
                        "    <o:AllowPNG/>\r\n" +
                        "    <o:PixelsPerInch>96</o:PixelsPerInch>\r\n" +
                        "  </o:OfficeDocumentSettings>\r\n" +
                        "</xml>\r\n" +
                        "<![endif]-->\r\n" +
                        "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" +
                        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" +
                        "  <meta name=\"x-apple-disable-message-reformatting\">\r\n" +
                        "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\r\n" +
                        "  <title></title>\r\n" +
                        "  \r\n" +
                        "    <style type=\"text/css\">\r\n" +
                        "      table, td { color: #000000; } @media (max-width: 480px) { #u_column_1 .v-col-padding { padding: 0px 0px 32px !important; } #u_content_text_1 .v-container-padding-padding { padding: 40px 20px 20px !important; } #u_content_text_2 .v-container-padding-padding { padding: 20px !important; } #u_column_29 .v-col-padding { padding: 30px 0px 0px !important; } #u_content_text_37 .v-container-padding-padding { padding: 0px 20px 5px !important; } #u_content_text_38 .v-container-padding-padding { padding: 10px 20px 15px !important; } }\r\n" +
                        "@media only screen and (min-width: 620px) {\r\n" +
                        "  .u-row {\r\n" +
                        "    width: 600px !important;\r\n" +
                        "  }\r\n" +
                        "  .u-row .u-col {\r\n" +
                        "    vertical-align: top;\r\n" +
                        "  }\r\n" +
                        "\r\n" +
                        "  .u-row .u-col-100 {\r\n" +
                        "    width: 600px !important;\r\n" +
                        "  }\r\n" +
                        "\r\n" +
                        "}\r\n" +
                        "\r\n" +
                        "@media (max-width: 620px) {\r\n" +
                        "  .u-row-container {\r\n" +
                        "    max-width: 100% !important;\r\n" +
                        "    padding-left: 0px !important;\r\n" +
                        "    padding-right: 0px !important;\r\n" +
                        "  }\r\n" +
                        "  .u-row .u-col {\r\n" +
                        "    min-width: 320px !important;\r\n" +
                        "    max-width: 100% !important;\r\n" +
                        "    display: block !important;\r\n" +
                        "  }\r\n" +
                        "  .u-row {\r\n" +
                        "    width: calc(100% - 40px) !important;\r\n" +
                        "  }\r\n" +
                        "  .u-col {\r\n" +
                        "    width: 100% !important;\r\n" +
                        "  }\r\n" +
                        "  .u-col > div {\r\n" +
                        "    margin: 0 auto;\r\n" +
                        "  }\r\n" +
                        "}\r\n" +
                        "body {\r\n" +
                        "  margin: 0;\r\n" +
                        "  padding: 0;\r\n" +
                        "}\r\n" +
                        "\r\n" +
                        "table,\r\n" +
                        "tr,\r\n" +
                        "td {\r\n" +
                        "  vertical-align: top;\r\n" +
                        "  border-collapse: collapse;\r\n" +
                        "}\r\n" +
                        "\r\n" +
                        "p {\r\n" +
                        "  margin: 0;\r\n" +
                        "}\r\n" +
                        "\r\n" +
                        ".ie-container table,\r\n" +
                        ".mso-container table {\r\n" +
                        "  table-layout: fixed;\r\n" +
                        "}\r\n" +
                        "\r\n" +
                        "* {\r\n" +
                        "  line-height: inherit;\r\n" +
                        "}\r\n" +
                        "\r\n" +
                        "a[x-apple-data-detectors='true'] {\r\n" +
                        "  color: inherit !important;\r\n" +
                        "  text-decoration: none !important;\r\n" +
                        "}\r\n" +
                        "\r\n" +
                        "</style>\r\n" +
                        "  \r\n" +
                        "  \r\n" +
                        "\r\n" +
                        "<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Raleway:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\r\n" +
                        "\r\n" +
                        "</head>\r\n" +
                        "\r\n" +
                        "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #ffffff;color: #000000\">\r\n" +
                        "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\r\n" +
                        "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\r\n" +
                        "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #ffffff;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\r\n" +
                        "  <tbody>\r\n" +
                        "  <tr style=\"vertical-align: top\">\r\n" +
                        "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\r\n" +
                        "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #ffffff;\"><![endif]-->\r\n" +
                        "    \r\n" +
                        "\r\n" +
                        "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" +
                        "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\r\n" +
                        "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-image: url('images/image-1.png');background-repeat: no-repeat;background-position: center top;background-color: transparent;\">\r\n" +
                        "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-image: url('images/image-1.png');background-repeat: no-repeat;background-position: center top;background-color: transparent;\"><![endif]-->\r\n" +
                        "      \r\n" +
                        "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-padding\" style=\"background-color: #0d6efd;width: 600px;padding: 0px 0px 42px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\r\n" +
                        "<div id=\"u_column_1\" class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n" +
                        "  <div style=\"background-color: #0d6efd;width: 100% !important;\">\r\n" +
                        "  <!--[if (!mso)&(!IE)]><!--><div class=\"v-col-padding\" style=\"padding: 0px 0px 42px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n" +
                        "  \r\n" +
                        "<table id=\"u_content_text_1\" style=\"font-family:'Raleway',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" +
                        "  <tbody>\r\n" +
                        "    <tr>\r\n" +
                        "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:50px 40px 20px;font-family:'Raleway',sans-serif;\" align=\"left\">\r\n" +
                        "        \r\n" +
                        "  <div style=\"color: #ffffff; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n" +
                        "    <p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 26px; line-height: 36.4px;\"><strong><span style=\"line-height: 36.4px; font-size: 26px;\">오늘의 CS</span></strong></span></p>\r\n" +
                        "  </div>\r\n" +
                        "\r\n" +
                        "      </td>\r\n" +
                        "    </tr>\r\n" +
                        "  </tbody>\r\n" +
                        "</table>\r\n" +
                        "\r\n" +
                        "<table id=\"u_content_text_2\" style=\"font-family:'Raleway',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" +
                        "  <tbody>\r\n" +
                        "    <tr>\r\n" +
                        "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 40px;font-family:'Raleway',sans-serif;\" align=\"left\">\r\n" +
                        "        \r\n" +
                        "  <div style=\"color: #fcfcfc; line-height: 170%; text-align: left; word-wrap: break-word;\">\r\n" +
                        "    <p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-size: 14px; line-height: 23.8px;\"><span style=\"line-height: 23.8px; font-size: 14px;\"><span style=\"line-height: 23.8px; font-size: 14px;\">데이터 구조, 알고리즘, 운영체제, 네트워크 내용을 </span></span></span></p>\r\n" +
                        "<p style=\"font-size: 14px; line-height: 170%;\"><span style=\"font-size: 14px; line-height: 23.8px;\"><span style=\"line-height: 23.8px; font-size: 14px;\"><span style=\"line-height: 23.8px; font-size: 14px;\">부담없는 하루 분량으로 뉴스레터를 보내드려요 \uD83C\uDF81</span></span></span></p>\r\n" +
                        "  </div>\r\n" +
                        "\r\n" +
                        "      </td>\r\n" +
                        "    </tr>\r\n" +
                        "  </tbody>\r\n" +
                        "</table>\r\n" +
                        "\r\n" +
                        "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" +
                        "  </div>\r\n" +
                        "</div>\r\n" +
                        "<!--[if (mso)|(IE)]></td><![endif]-->\r\n" +
                        "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" +
                        "    </div>\r\n" +
                        "  </div>\r\n" +
                        "</div>\r\n" +
                        "\r\n" +
                        "\r\n" +
                        "\r\n" +
                        "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\r\n" +
                        "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f9f9f9;\">\r\n" +
                        "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\r\n" +
                        "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #f9f9f9;\"><![endif]-->\r\n" +
                        "      \r\n" +
                        "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" class=\"v-col-padding\" style=\"width: 600px;padding: 30px 30px 60px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\r\n" +
                        "<div id=\"u_column_29\" class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\r\n" +
                        "  <div style=\"width: 100% !important;\">\r\n" +
                        "  <!--[if (!mso)&(!IE)]><!--><div class=\"v-col-padding\" style=\"padding: 30px 30px 60px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\"><!--<![endif]-->\r\n" +
                        "  \r\n" +
                        "<table id=\"u_content_text_37\" style=\"font-family:'Raleway',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" +
                        "  <tbody>\r\n" +
                        "    <tr>\r\n" +
                        "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 10px;font-family:'Raleway',sans-serif;\" align=\"left\">\r\n" +
                        "        \r\n" +
                        "  <div style=\"color: #0d6efd; line-height: 140%; text-align: left; word-wrap: break-word;\">\r\n" +
                        "    <p style=\"font-size: 14px; line-height: 140%; text-align: left;\"><span style=\"font-size: 22px; line-height: 30.8px;\"><strong><span style=\"line-height: 30.8px; font-size: 22px;\">구독해주셔서 감사합니다!</span></strong></span></p>\r\n" +
                        "  </div>\r\n" +
                        "\r\n" +
                        "      </td>\r\n" +
                        "    </tr>\r\n" +
                        "  </tbody>\r\n" +
                        "</table>\r\n" +
                        "\r\n" +
                        "<table id=\"u_content_text_38\" style=\"font-family:'Raleway',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\r\n" +
                        "  <tbody>\r\n" +
                        "    <tr>\r\n" +
                        "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Raleway',sans-serif;\" align=\"left\">\r\n" +
                        "        \r\n" +
                        "  <div style=\"color: #000000; line-height: 170%; text-align: left; word-wrap: break-word;\">\r\n" +
                        "    <p style=\"font-size: 14px; line-height: 170%;\">오늘의 CS를 구독해주셔서 감사합니다!</p>\r\n" +
                        "<p style=\"font-size: 14px; line-height: 170%;\">구독자분들께 양질의 서비스를 제공하고자 최선을 다하겠습니다. \uD83D\uDE01</p>\r\n" +
                        "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\r\n" +
                        "<p style=\"font-size: 14px; line-height: 170%;\">오늘의 CS 뉴스레터는 신청해주신 날짜 간격에 맞춰 아침 8시 전후로 전송됩니다. </p>\r\n" +
                        "<p style=\"font-size: 14px; line-height: 170%;\">뉴스레터가 스팸 메일함에 전송될 수도 있으니 확인 부탁드립니다!</p>\r\n" +
                        "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\r\n" +
                        "<p style=\"font-size: 14px; line-height: 170%;\">그러면 첫 번째 오늘의 CS 뉴스레터에서 만나요~ \uD83D\uDE4C\uD83D\uDE4C</p>\r\n" +
                        "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\r\n" +
                        "<p style=\"font-size: 14px; line-height: 170%;\"><strong>오늘의 CS 드림</strong></p>\r\n" +
                        "  </div>\r\n" +
                        "\r\n" +
                        "      </td>\r\n" +
                        "    </tr>\r\n" +
                        "  </tbody>\r\n" +
                        "</table>\r\n" +
                        "\r\n" +
                        "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\r\n" +
                        "  </div>\r\n" +
                        "</div>\r\n" +
                        "<!--[if (mso)|(IE)]></td><![endif]-->\r\n" +
                        "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\r\n" +
                        "    </div>\r\n" +
                        "  </div>\r\n" +
                        "</div>\r\n" +
                        "\r\n" +
                        "\r\n" +
                        "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\r\n" +
                        "    </td>\r\n" +
                        "  </tr>\r\n" +
                        "  </tbody>\r\n" +
                        "  </table>\r\n" +
                        "  <!--[if mso]></div><![endif]-->\r\n" +
                        "  <!--[if IE]></div><![endif]-->\r\n" +
                        "</body>\r\n" +
                        "\r\n" +
                        "</html>\r\n";
    }

    private WelcomeMail() {
    }
}
