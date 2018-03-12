
package html

import play.twirl.api.Html
import html.main
import io.skysail.domain.ResponseEventBase
import io.skysail.server.RepresentationModel

object head extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.3*/("""<meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Skysail</title>

  <link rel="shortcut icon" href="/webapp/favicon.ico" type="image/x-icon" >
  <link rel="stylesheet"    href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css" async>
  <link rel="stylesheet"    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet"    href="https://fonts.googleapis.com/css?family=Inconsolata|Roboto|Slabo+27px" async>
  <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.20.1/moment-with-locales.min.js"></script>

 <!-- <script src="https://polygit.org/components/webcomponentsjs/webcomponents-loader.js"></script>

  <link rel="import"  href="https://polygit.org/components/polymer/lib/elements/dom-repeat.html">

  <link rel="import" href="/_root/custom-element.html">
  <link rel="import" href="/demo/v1/dom-element.html">-->


  <!--<script type="text/javascript" src="/_ui/jquery/3.1.1/jquery-3.1.1.min.js"></script>

    <link href="/webapp/css/contextMenu/jquery.contextMenu.css" rel="stylesheet" type="text/css" async />
    <script type="text/javascript" src="/webapp/js/contextMenu/jquery.contextMenu.js" async></script>

    <script type="text/javascript" src="/_polymer/bower_components/noty/js/noty/packaged/jquery.noty.packaged.min.js"></script>
    -->
<!--  <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>-->
    <!-- <script type="text/javascript" src="/_polymer/bower_components/webcomponentsjs/webcomponents-lite.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/webapp/css/hover/hover-min.css">-->

  <!-- Custom styles for this template
  <link href="custom.css" rel="stylesheet">
  -->

  <script
          src="https://code.jquery.com/jquery-3.3.1.min.js"
          integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
          crossorigin="anonymous"></script>
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Mar 12 17:48:20 CET 2018
                  SOURCE: C:/git/skysail-server/skysail.server/./src/head.scala.html
                  HASH: e02b8b54e32402700d5813b28fb56e716c690083
                  MATRIX: 532->2
                  LINES: 17->1
                  -- GENERATED --
              */
          