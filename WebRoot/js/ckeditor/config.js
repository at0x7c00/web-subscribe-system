/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar = [[
       'Bold', 'Italic', 'RemoveFormat',
       '-',
       'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock',
       '-',
       'Font', 'FontSize', 'TextColor', 'BGColor',
       '-',
       'Table', 'Image', 'Flash', 'Link', 'Unlink',
       '-',
       'Source','About'
   ]];

   config.forcePasteAsPlainText = true;
   config.pasteFromWordRemoveFontStyles = true;
   config.pasteFromWordRemoveStyles = true;
   
	// Remove some buttons, provided by the standard plugins, which we don't
	// need to have in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';

	// Se the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';
	config.fullPage = true;

	// Make dialogs simpler. 
	config.removeDialogTabs = 'image:advanced;link:advanced';
	config.filebrowserImageUploadUrl="filee/ckeditorupload.do?type=images";
	config.filebrowserUploadUrl="filee/ckeditorupload.do?type=attachment";
	 
	//config.filebrowserUploadUrl = "ckeditorUpload.action";
};
