package cn.yu.master.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public final class MimeUtils {

	private static final String TAG = "MimeUtils";

	public static String MIMETYPE_UNKNOWN = "MIMETPE_UNKNOWN";

	private static final Map<String, String> mimeTypeToExtensionMap = new HashMap<String, String>();

	private static final Map<String, String> extensionToMimeTypeMap = new HashMap<String, String>();

	public static void openFile(Context context, File file) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		String mimeType = getMIMEType(file);
		if (MIMETYPE_UNKNOWN.equals(mimeType) || mimeType == null) {
			Toast.makeText(context, "Unknow Type", Toast.LENGTH_SHORT).show();
			return;
		}
		if (mimeType.indexOf("audio/") != -1)
			mimeType = "audio/*";
		else if (mimeType.indexOf("video/") != -1)
			mimeType = "video/*";
		else if (mimeType.compareTo(MIMETYPE_UNKNOWN) == 0 || mimeType == null)
			mimeType = "application/*";

		intent.setDataAndType(Uri.fromFile(file), mimeType);
		try {
			((Activity) context).startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(context, "Unknow Type", Toast.LENGTH_SHORT).show();
		}
	}

	public static String getMIMEType(File file) {
		String fName = file.getName();
		String mimeType = getMIME(fName);
		return mimeType;
	}

	public static String getMIME(String fName) {
		String mimeType = MIMETYPE_UNKNOWN;
		int dotIndex = fName.lastIndexOf(".");
		if (dotIndex != -1) {
			String end = fName.substring(dotIndex + 1, fName.length())
					.toLowerCase();
			if (mimeType.compareTo(MIMETYPE_UNKNOWN) == 0 || mimeType == null) {
				mimeType = guessMimeTypeFromExtension(end);
				if (mimeType == null) {
					mimeType = MIMETYPE_UNKNOWN;
				}
			}
		}
		return mimeType;
	}

	public static boolean isTextMIME(String fName) {
		String mime = getMIME(fName);
		if (mime.startsWith("text")) {
			return true;
		}
		return false;
	}

	static {
		add("application/andrew-inset", "ez");
		add("application/dsptype", "tsp");
		add("application/futuresplash", "spl");
		add("application/hta", "hta");
		add("application/mac-binhex40", "hqx");
		add("application/mac-compactpro", "cpt");
		add("application/mathematica", "nb");
		add("application/msaccess", "mdb");
		add("application/oda", "oda");
		add("application/ogg", "ogg");
		add("application/pdf", "pdf");
		add("application/pgp-keys", "key");
		add("application/pgp-signature", "pgp");
		add("application/pics-rules", "prf");
		add("application/rar", "rar");
		add("application/rdf+xml", "rdf");
		add("application/rss+xml", "rss");
		add("application/zip", "zip");
		add("application/vnd.android.package-archive", "apk");
		add("application/vnd.cinderella", "cdy");
		add("application/vnd.ms-pki.stl", "stl");
		add("application/vnd.oasis.opendocument.database", "odb");
		add("application/vnd.oasis.opendocument.formula", "odf");
		add("application/vnd.oasis.opendocument.graphics", "odg");
		add("application/vnd.oasis.opendocument.graphics-template", "otg");
		add("application/vnd.oasis.opendocument.image", "odi");
		add("application/vnd.oasis.opendocument.spreadsheet", "ods");
		add("application/vnd.oasis.opendocument.spreadsheet-template", "ots");
		add("application/vnd.oasis.opendocument.text", "odt");
		add("application/vnd.oasis.opendocument.text-master", "odm");
		add("application/vnd.oasis.opendocument.text-template", "ott");
		add("application/vnd.oasis.opendocument.text-web", "oth");
		add("application/vnd.google-earth.kml+xml", "kml");
		add("application/vnd.google-earth.kmz", "kmz");
		add("application/msword", "doc");
		add("application/msword", "dot");
		add("application/vnd.openxmlformats-officedocument.wordprocessingml.document",
				"docx");
		add("application/vnd.openxmlformats-officedocument.wordprocessingml.template",
				"dotx");
		add("application/vnd.ms-excel", "xls");
		add("application/vnd.ms-excel", "xlt");
		add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
				"xlsx");
		add("application/vnd.openxmlformats-officedocument.spreadsheetml.template",
				"xltx");
		add("application/vnd.ms-powerpoint", "ppt");
		add("application/vnd.ms-powerpoint", "pot");
		add("application/vnd.ms-powerpoint", "pps");
		add("application/vnd.openxmlformats-officedocument.presentationml.presentation",
				"pptx");
		add("application/vnd.openxmlformats-officedocument.presentationml.template",
				"potx");
		add("application/vnd.openxmlformats-officedocument.presentationml.slideshow",
				"ppsx");
		add("application/vnd.rim.cod", "cod");
		add("application/vnd.smaf", "mmf");
		add("application/vnd.stardivision.calc", "sdc");
		add("application/vnd.stardivision.draw", "sda");
		add("application/vnd.stardivision.impress", "sdd");
		add("application/vnd.stardivision.impress", "sdp");
		add("application/vnd.stardivision.math", "smf");
		add("application/vnd.stardivision.writer", "sdw");
		add("application/vnd.stardivision.writer", "vor");
		add("application/vnd.stardivision.writer-global", "sgl");
		add("application/vnd.sun.xml.calc", "sxc");
		add("application/vnd.sun.xml.calc.template", "stc");
		add("application/vnd.sun.xml.draw", "sxd");
		add("application/vnd.sun.xml.draw.template", "std");
		add("application/vnd.sun.xml.impress", "sxi");
		add("application/vnd.sun.xml.impress.template", "sti");
		add("application/vnd.sun.xml.math", "sxm");
		add("application/vnd.sun.xml.writer", "sxw");
		add("application/vnd.sun.xml.writer.global", "sxg");
		add("application/vnd.sun.xml.writer.template", "stw");
		add("application/vnd.visio", "vsd");
		add("application/x-abiword", "abw");
		add("application/x-apple-diskimage", "dmg");
		add("application/x-bcpio", "bcpio");
		add("application/x-bittorrent", "torrent");
		add("application/x-cdf", "cdf");
		add("application/x-cdlink", "vcd");
		add("application/x-chess-pgn", "pgn");
		add("application/x-cpio", "cpio");
		add("application/x-debian-package", "deb");
		add("application/x-debian-package", "udeb");
		add("application/x-director", "dcr");
		add("application/x-director", "dir");
		add("application/x-director", "dxr");
		add("application/x-dms", "dms");
		add("application/x-doom", "wad");
		add("application/x-dvi", "dvi");
		add("application/x-flac", "flac");
		add("application/x-font", "pfa");
		add("application/x-font", "pfb");
		add("application/x-font", "gsf");
		add("application/x-font", "pcf");
		add("application/x-font", "pcf.Z");
		add("application/x-freemind", "mm");
		add("application/x-futuresplash", "spl");
		add("application/x-gnumeric", "gnumeric");
		add("application/x-go-sgf", "sgf");
		add("application/x-graphing-calculator", "gcf");
		add("application/x-gtar", "gtar");
		add("application/x-gtar", "tgz");
		add("application/x-gtar", "taz");
		add("application/x-hdf", "hdf");
		add("application/x-ica", "ica");
		add("application/x-internet-signup", "ins");
		add("application/x-internet-signup", "isp");
		add("application/x-iphone", "iii");
		add("application/x-iso9660-image", "iso");
		add("application/x-jmol", "jmz");
		add("application/x-kchart", "chrt");
		add("application/x-killustrator", "kil");
		add("application/x-koan", "skp");
		add("application/x-koan", "skd");
		add("application/x-koan", "skt");
		add("application/x-koan", "skm");
		add("application/x-kpresenter", "kpr");
		add("application/x-kpresenter", "kpt");
		add("application/x-kspread", "ksp");
		add("application/x-kword", "kwd");
		add("application/x-kword", "kwt");
		add("application/x-latex", "latex");
		add("application/x-lha", "lha");
		add("application/x-lzh", "lzh");
		add("application/x-lzx", "lzx");
		add("application/x-maker", "frm");
		add("application/x-maker", "maker");
		add("application/x-maker", "frame");
		add("application/x-maker", "fb");
		add("application/x-maker", "book");
		add("application/x-maker", "fbdoc");
		add("application/x-mif", "mif");
		add("application/x-ms-wmd", "wmd");
		add("application/x-ms-wmz", "wmz");
		add("application/x-msi", "msi");
		add("application/x-ns-proxy-autoconfig", "pac");
		add("application/x-nwc", "nwc");
		add("application/x-object", "o");
		add("application/x-oz-application", "oza");
		add("application/x-pkcs12", "p12");
		add("application/x-pkcs12", "pfx");
		add("application/x-pkcs7-certreqresp", "p7r");
		add("application/x-pkcs7-crl", "crl");
		add("application/x-quicktimeplayer", "qtl");
		add("application/x-shar", "shar");
		add("application/x-shockwave-flash", "swf");
		add("application/x-stuffit", "sit");
		add("application/x-sv4cpio", "sv4cpio");
		add("application/x-sv4crc", "sv4crc");
		add("application/x-tar", "tar");
		add("application/x-texinfo", "texinfo");
		add("application/x-texinfo", "texi");
		add("application/x-troff", "t");
		add("application/x-troff", "roff");
		add("application/x-troff-man", "man");
		add("application/x-ustar", "ustar");
		add("application/x-wais-source", "src");
		add("application/x-wingz", "wz");
		add("application/x-webarchive", "webarchive");
		add("application/x-webarchive-xml", "webarchivexml");
		add("application/x-x509-ca-cert", "crt");
		add("application/x-x509-user-cert", "crt");
		add("application/x-xcf", "xcf");
		add("application/x-xfig", "fig");
		add("application/xhtml+xml", "xhtml");
		add("audio/3gpp", "3gpp");
		add("audio/amr", "amr");
		add("audio/basic", "snd");
		add("audio/midi", "mid");
		add("audio/midi", "midi");
		add("audio/midi", "kar");
		add("audio/midi", "xmf");
		add("audio/mobile-xmf", "mxmf");
		add("audio/mpeg", "mpga");
		add("audio/mpeg", "mpega");
		add("audio/mpeg", "mp2");
		add("audio/mpeg", "mp3");
		add("audio/mpeg", "m4a");
		add("audio/mpegurl", "m3u");
		add("audio/prs.sid", "sid");
		add("audio/x-aiff", "aif");
		add("audio/x-aiff", "aiff");
		add("audio/x-aiff", "aifc");
		add("audio/x-gsm", "gsm");
		add("audio/x-mpegurl", "m3u");
		add("audio/x-ms-wma", "wma");
		add("audio/x-ms-wax", "wax");
		add("audio/x-pn-realaudio", "ra");
		add("audio/x-pn-realaudio", "rm");
		add("audio/x-pn-realaudio", "ram");
		add("audio/x-realaudio", "ra");
		add("audio/x-scpls", "pls");
		add("audio/x-sd2", "sd2");
		add("audio/x-wav", "wav");
		add("image/bmp", "bmp");
		add("image/gif", "gif");
		add("image/ico", "cur");
		add("image/ico", "ico");
		add("image/ief", "ief");
		add("image/jpeg", "jpeg");
		add("image/jpeg", "jpg");
		add("image/jpeg", "jpe");
		add("image/pcx", "pcx");
		add("image/png", "png");
		add("image/svg+xml", "svg");
		add("image/svg+xml", "svgz");
		add("image/tiff", "tiff");
		add("image/tiff", "tif");
		add("image/vnd.djvu", "djvu");
		add("image/vnd.djvu", "djv");
		add("image/vnd.wap.wbmp", "wbmp");
		add("image/x-cmu-raster", "ras");
		add("image/x-coreldraw", "cdr");
		add("image/x-coreldrawpattern", "pat");
		add("image/x-coreldrawtemplate", "cdt");
		add("image/x-corelphotopaint", "cpt");
		add("image/x-icon", "ico");
		add("image/x-jg", "art");
		add("image/x-jng", "jng");
		add("image/x-ms-bmp", "bmp");
		add("image/x-photoshop", "psd");
		add("image/x-portable-anymap", "pnm");
		add("image/x-portable-bitmap", "pbm");
		add("image/x-portable-graymap", "pgm");
		add("image/x-portable-pixmap", "ppm");
		add("image/x-rgb", "rgb");
		add("image/x-xbitmap", "xbm");
		add("image/x-xpixmap", "xpm");
		add("image/x-xwindowdump", "xwd");
		add("model/iges", "igs");
		add("model/iges", "iges");
		add("model/mesh", "msh");
		add("model/mesh", "mesh");
		add("model/mesh", "silo");
		add("text/calendar", "ics");
		add("text/calendar", "icz");
		add("text/comma-separated-values", "csv");
		add("text/css", "css");
		add("text/html", "htm");
		add("text/html", "html");
		add("text/h323", "323");
		add("text/iuls", "uls");
		add("text/mathml", "mml");
		// add ".txt" first so it will be the default for ExtensionFromMimeType
		add("text/plain", "txt");
		add("text/plain", "asc");
		add("text/plain", "text");
		add("text/plain", "diff");
		add("text/plain", "po"); // reserve "pot" for vnd.ms-powerpoint
		add("text/richtext", "rtx");
		add("text/rtf", "rtf");
		add("text/texmacs", "ts");
		add("text/text", "phps");
		add("text/tab-separated-values", "tsv");
		add("text/xml", "xml");
		add("text/x-bibtex", "bib");
		add("text/x-boo", "boo");
		add("text/x-c++hdr", "h++");
		add("text/x-c++hdr", "hpp");
		add("text/x-c++hdr", "hxx");
		add("text/x-c++hdr", "hh");
		add("text/x-c++src", "c++");
		add("text/x-c++src", "cpp");
		add("text/x-c++src", "cxx");
		add("text/x-chdr", "h");
		add("text/x-component", "htc");
		add("text/x-csh", "csh");
		add("text/x-csrc", "c");
		add("text/x-dsrc", "d");
		add("text/x-haskell", "hs");
		add("text/x-java", "java");
		add("text/x-literate-haskell", "lhs");
		add("text/x-moc", "moc");
		add("text/x-pascal", "p");
		add("text/x-pascal", "pas");
		add("text/x-pcs-gcd", "gcd");
		add("text/x-setext", "etx");
		add("text/x-tcl", "tcl");
		add("text/x-tex", "tex");
		add("text/x-tex", "ltx");
		add("text/x-tex", "sty");
		add("text/x-tex", "cls");
		add("text/x-vcalendar", "vcs");
		add("text/x-vcard", "vcf");
		add("video/3gpp", "3gpp");
		add("video/3gpp", "3gp");
		add("video/3gpp", "3g2");
		add("video/dl", "dl");
		add("video/dv", "dif");
		add("video/dv", "dv");
		add("video/fli", "fli");
		add("video/m4v", "m4v");
		add("video/mpeg", "mpeg");
		add("video/mpeg", "mpg");
		add("video/mpeg", "mpe");
		add("video/mp4", "mp4");
		add("video/mpeg", "VOB");
		add("video/quicktime", "qt");
		add("video/quicktime", "mov");
		add("video/vnd.mpegurl", "mxu");
		add("video/x-la-asf", "lsf");
		add("video/x-la-asf", "lsx");
		add("video/x-mng", "mng");
		add("video/x-ms-asf", "asf");
		add("video/x-ms-asf", "asx");
		add("video/x-ms-wm", "wm");
		add("video/x-ms-wmv", "wmv");
		add("video/x-ms-wmx", "wmx");
		add("video/x-ms-wvx", "wvx");
		add("video/x-msvideo", "avi");
		add("video/x-sgi-movie", "movie");
		add("video/x-webex", "wrf");
		add("video/x-matroska", "mkv");
		add("video/webm", "webm");
		add("x-conference/x-cooltalk", "ice");
		add("x-epoc/x-sisx-app", "sisx");
	}

	private static void add(String mimeType, String extension) {
		if (!mimeTypeToExtensionMap.containsKey(mimeType)) {
			mimeTypeToExtensionMap.put(mimeType, extension);
		}
		extensionToMimeTypeMap.put(extension, mimeType);
	}

	private MimeUtils() {
	}

	public static boolean hasMimeType(String mimeType) {
		if (mimeType == null || mimeType.isEmpty()) {
			return false;
		}
		return mimeTypeToExtensionMap.containsKey(mimeType);
	}

	public static String guessMimeTypeFromExtension(String extension) {
		if (extension == null || extension.isEmpty()) {
			return null;
		}
		Log.e(TAG, ">>>>>>>>>>>>>>receive file type = " + extension);
		return extensionToMimeTypeMap.get(extension);
	}

	public static boolean hasExtension(String extension) {
		if (extension == null || extension.isEmpty()) {
			return false;
		}
		return extensionToMimeTypeMap.containsKey(extension);
	}

	public static String guessExtensionFromMimeType(String mimeType) {
		if (mimeType == null || mimeType.isEmpty()) {
			return null;
		}
		return mimeTypeToExtensionMap.get(mimeType);
	}
}
