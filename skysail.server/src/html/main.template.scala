
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object main extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[ResponseEventBase,Html,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(response: ResponseEventBase)(content: Html):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.46*/("""
"""),format.raw/*2.1*/("""<!DOCTYPE html>
<meta charset="utf-8"/>
<html lang="en">
<head>
    """),_display_(/*6.6*/head()),format.raw/*6.12*/("""
"""),format.raw/*7.1*/("""</head>

<body style="height:100%">

    """),_display_(/*11.6*/navbar()),format.raw/*11.14*/("""

    """),format.raw/*13.5*/("""<div class="container-fluid">
        <div class="row">
            <nav class="col-md-2 d-none d-md-block bg-light sidebar">
                <div class="sidebar-sticky">
                    <ul class="nav flex-column">
                        <li class="nav-item">
                            <a class="nav-link active" href="#">
                                <span data-feather="home"></span>
                                Applications <span class="sr-only">(current)</span>
                                <div id="appnav"></div>
                            </a>
                        </li>
                    </ul>

                    <!--<h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                        <span>Saved reports</span>
                        <a class="d-flex align-items-center text-muted" href="#">
                            <span data-feather="plus-circle"></span>
                        </a>
                    </h6>
                    <ul class="nav flex-column mb-2">
                        <li class="nav-item">
                            <a class="nav-link" href="#">
                                <span data-feather="file-text"></span>
                                Current month
                            </a>
                        </li>

                    </ul>-->
                </div>
            </nav>

            <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
                """),_display_(/*46.18*/content),format.raw/*46.25*/("""
            """),format.raw/*47.13*/("""</main>
        </div>
    </div>

    <script>

      function createElement(i, name,context) """),format.raw/*53.47*/("""{"""),format.raw/*53.48*/("""
        """),format.raw/*54.9*/("""return $('<div>', """),format.raw/*54.27*/("""{"""),format.raw/*54.28*/("""
          """),format.raw/*55.11*/("""html: $('<a>', """),format.raw/*55.26*/("""{"""),format.raw/*55.27*/("""
            """),format.raw/*56.13*/("""href: context,
            text: name
          """),format.raw/*58.11*/("""}"""),format.raw/*58.12*/(""")
        """),format.raw/*59.9*/("""}"""),format.raw/*59.10*/(""")
      """),format.raw/*60.7*/("""}"""),format.raw/*60.8*/("""

      """),format.raw/*62.7*/("""$.get( "/_root/apps", function( data ) """),format.raw/*62.46*/("""{"""),format.raw/*62.47*/("""
        """),format.raw/*63.9*/("""var c=$("#appnav");
        $.each(data.applications, function(i, obj) """),format.raw/*64.52*/("""{"""),format.raw/*64.53*/("""
          """),format.raw/*65.11*/("""c.append(createElement(1, obj.name, obj.context));
        """),format.raw/*66.9*/("""}"""),format.raw/*66.10*/(""");
      """),format.raw/*67.7*/("""}"""),format.raw/*67.8*/(""", 'json');

    </script>


    <!--<custom-element></custom-element>-->


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>"""))
      }
    }
  }

  def render(response:ResponseEventBase,content:Html): play.twirl.api.HtmlFormat.Appendable = apply(response)(content)

  def f:((ResponseEventBase) => (Html) => play.twirl.api.HtmlFormat.Appendable) = (response) => (content) => apply(response)(content)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Mar 12 17:48:20 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server/./src/main.scala.html
                  HASH: 1df09289adf8763d31098875d63dc649baa7b9b5
                  MATRIX: 466->1|605->45|632->46|726->115|752->121|779->122|847->164|876->172|909->178|2456->1698|2484->1705|2525->1718|2648->1813|2677->1814|2713->1823|2759->1841|2788->1842|2827->1853|2870->1868|2899->1869|2940->1882|3016->1930|3045->1931|3082->1941|3111->1942|3146->1950|3174->1951|3209->1959|3276->1998|3305->1999|3341->2008|3440->2079|3469->2080|3508->2091|3594->2150|3623->2151|3659->2160|3687->2161
                  LINES: 12->1|17->1|18->2|22->6|22->6|23->7|27->11|27->11|29->13|62->46|62->46|63->47|69->53|69->53|70->54|70->54|70->54|71->55|71->55|71->55|72->56|74->58|74->58|75->59|75->59|76->60|76->60|78->62|78->62|78->62|79->63|80->64|80->64|81->65|82->66|82->66|83->67|83->67
                  -- GENERATED --
              */
          