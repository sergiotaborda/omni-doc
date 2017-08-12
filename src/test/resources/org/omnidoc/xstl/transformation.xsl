<?xml version="1.0" encoding="ISO-8859-1" ?>

<!DOCTYPE stylesheet [
<!ENTITY plusmn  "&#177;" ><!-- the +- sign -->
]>

<xsl:stylesheet version="2.0" 
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:fn="http://www.w3.org/2005/xpath-functions"
	xmlns:xdt="http://www.w3.org/2005/xpath-datatypes"
	xmlns:err="http://www.w3.org/2005/xqt-errors"
	exclude-result-prefixes="xs xdt err fn">

	<xsl:output method="xml"  indent="yes"/>
	
	<xsl:template match="*|text()|node()">
		<xsl:copy>
		       <xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="document">
		<div>
			 
			 	<xsl:apply-templates select="body"/>

				<xsl:apply-templates select="references"/>
		
		</div>	
	</xsl:template>
	
	<xsl:template match="body">
		<div class="article" >
			
			<h1><xsl:value-of select="//properties/title/text()"/></h1>
			<div id="pages" >
				<xsl:apply-templates/>
			</div>
		</div>
	</xsl:template>
	
	<xsl:template match="toc">
		<table class="table.toc">
		<xsl:for-each select="/body/section">
			<tr>
			<td>
			<a href="{@name}">	<xsl:value-of select="@name"/> </a>
			</td>
			</tr>
		</xsl:for-each>
		</table>
	</xsl:template>
	
	<xsl:template match="note">
		<sup><xsl:number count="note" level="any" /></sup>
	</xsl:template>
		
	
	<xsl:template match="references">
		<div class="references" >
		<h3 class="ttl">Referências</h3>
		<table class="referencetable">
		<xsl:for-each select="ref">
			<tr>
				<td align="center" valign="top">
				[<xsl:value-of select="@id"/>]
				</td>
				<td align="left" valign="top">
				<strong>
				<xsl:value-of select="title"/>
				</strong><br/>
				<i>
				<xsl:for-each select="author">
					<xsl:value-of select="text()"/>
					<xsl:if test="position() != last()" >
					    <xsl:text>, </xsl:text>
					</xsl:if>
				</xsl:for-each>
				</i><br/>

				<xsl:if test="string-length(book/text()) &gt; 0">
				Livro:<a href="/library/books/{book/text()}.html" title="{title/text()}" ><xsl:value-of select="title/text()"/></a><br/>
				</xsl:if>
				
				<xsl:if test="string-length(editor/text()) &gt; 0">
				Editor:<xsl:apply-templates match="editor"/><br/>
				</xsl:if>
				
				<xsl:if test="string-length(url/text()) &gt; 0">
				URL:<xsl:apply-templates match="url"/><br/>
				</xsl:if>
				
				<p><xsl:value-of select="abstract"/></p>
				</td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	
	<xsl:template match="url">
		<a href="{text()}" target="_new" rel="nofollow"><xsl:value-of select="text()"/></a>
	</xsl:template>
	
	<xsl:template match="a">
		<a href="{@href}" ><xsl:value-of select="text()"/></a>
	</xsl:template>
	
	<xsl:template match="body/section">
		
		<h2 class="ttl"><xsl:value-of select="@name"/></h2>

		<xsl:apply-templates/>

	</xsl:template>
	
	<xsl:template match="page/section">
		
		<h2 class="ttl"><xsl:value-of select="@name"/></h2>

		<xsl:apply-templates/>

	</xsl:template>
	
	<xsl:template match="page">
		<div class="page" >
			<xsl:apply-templates/>
		</div>

	</xsl:template>
	
	<xsl:template match="section/section">

		<h3><xsl:value-of select="@name"/></h3>

		<xsl:apply-templates/>

	</xsl:template>
	
	<xsl:template match="section/section/section">

		<h4><xsl:value-of select="@name"/></h4>

		<xsl:apply-templates/>

	</xsl:template>
	
	<xsl:template match="font|table">
		<xsl:copy>
		       <xsl:apply-templates select="@* | node()"/>
		</xsl:copy>
	</xsl:template>
	
	<xsl:template match="etable">
		<table colspan="0" align="center" border="1"  >
			 <xsl:apply-templates select="@* | node()"/>
		</table>
	</xsl:template>
	
	<xsl:template match="plusminus">
		&plusmn;
	</xsl:template>
	<xsl:template match="quote">
		<table colspan="0" align="center" border="0"  >
		<tr align="center" bgcolor="C0C0C0">
		<td>
			<i><pre>
				<xsl:copy>
				       <xsl:apply-templates/>
				</xsl:copy>
			</pre></i>
		</td>
		<xsl:choose>
		  <xsl:when test="string-length(@author)">
		   <tr align="right">
		     <td ><xsl:value-of select="@author"/></td>
		   </tr>
		  </xsl:when>
		</xsl:choose>
		</tr>
		</table>
		
	</xsl:template>
	
	<xsl:template match="source">
		
		<div class="sourceCodeDisplay">
		<xsl:choose>
		  <xsl:when test="string-length(@language)">
		 	 <pre name="source" class="brush: {@language}; highlight: [{@highlight}];" >
		 	 <xsl:value-of select="text()"/>
			 </pre>
		  </xsl:when>
		  <xsl:otherwise>
		  	<pre name="source" class="brush: java; highlight: [{@highlight}];" >
		 	 <xsl:value-of select="text()"/>
			 </pre>
		  </xsl:otherwise>
		</xsl:choose>
		
		
		
		
		<div class="legend">
		Código <xsl:number count="source" level="any" />: <xsl:apply-templates select="@legend"/>
		</div>
		</div>
		
	</xsl:template>
	
	<xsl:template match="figure">
		<table class="figure" align="center">
		<tr>
		<td align="center">
			<img class="ilustration" src="/img/ilust/{img/@src}" alt="{legend/text()}" />
		</td>
		</tr>
		<tr>
		<td class="legend"><p>Ilustração <xsl:number count="figure" level="any" />: <xsl:apply-templates select="legend"/></p></td>
		</tr>
		</table>
	</xsl:template>
	<xsl:template match="legend">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="emph">
		<i><xsl:value-of select="text()"/></i>
	</xsl:template>
	
	<xsl:template match="keyword">
		<span class="keyword" ><xsl:value-of select="text()"/></span>
	</xsl:template>
	
	<xsl:template match="class">
		<span class="codeType" name="{@name}" ><xsl:value-of select="text()"/></span>
	</xsl:template>

	<xsl:template match="author">
		<meta value="author" content="{text()}"/>
	</xsl:template>
	
	<xsl:template match="title">
		<title><xsl:copy-of select="text()"/></title> 
	</xsl:template>
	
	<xsl:template match="p">
		<p align="justify"><xsl:apply-templates/></p> 
	</xsl:template>
	
	<xsl:template match="img">
		<img src="img/ilust/{@src}" />
	</xsl:template>
	
	<xsl:template match="wiki">
		<xsl:choose>
			<xsl:when test="string-length(@lang)">
				<xsl:choose>
					<xsl:when test="string-length(@key)">
						<a href="http://{@lang}.wikipedia.org/wiki/{@key}" rel="nofollow" target="_new"> <xsl:apply-templates/></a>
					</xsl:when>
					<xsl:otherwise>
						<a href="http://{@lang}.wikipedia.org/wiki/{text()}" rel="nofollow" target="_new"> <xsl:apply-templates/></a>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="string-length(@key)">
						<a href="http://{@pt}.wikipedia.org/wiki/{@key}" rel="nofollow" target="_new"> <xsl:apply-templates/></a>
					</xsl:when>
					<xsl:otherwise>
						<a href="http://pt.wikipedia.org/wiki/{text()}" rel="nofollow" target="_new"> <xsl:apply-templates/></a>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="estrang">
		<i class="estrang"> <xsl:apply-templates/></i>
	</xsl:template>

	<xsl:template match="ref">
		<sup>[<xsl:value-of select="@id"/>]</sup>
	</xsl:template>
	
	<xsl:template match="pattern">
		<xsl:choose>
		  <xsl:when test="string-length(@ref)">
		   <span class="pattern" ref="{@ref}"><xsl:value-of select="text()"/></span>
		  </xsl:when>
		  <xsl:otherwise>
		   <span class="pattern" ><xsl:value-of select="text()"/></span>
		  </xsl:otherwise>
		</xsl:choose>
		
	</xsl:template>

</xsl:stylesheet>
