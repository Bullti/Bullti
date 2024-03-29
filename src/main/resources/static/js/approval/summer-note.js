/**
 * 
 */
$(document)
	.ready(
		function() {
			$('#summernote')
				.summernote(
					{
						toolbar: [
							['fontname',
								['fontname']],
							['fontsize',
								['fontsize']],
							[
								'style',
								[
									'bold',
									'italic',
									'underline',
									'strikethrough',
									'clear']],
							[
								'color',
								['forecolor',
									'color']],
							['table', ['table']],
							[
								'para',
								['ul', 'ol',
									'paragraph']],
							['height', ['height']],
							[
								'view',
								['fullscreen',
									'help']]],
						height: "500",
						lang: "ko-KR",
						fontNames: ['Arial',
							'Arial Black',
							'Comic Sans MS',
							'Courier New', '맑은 고딕',
							'궁서', '굴림체', '굴림', '돋움체',
							'바탕체'],
						fontSizes: ['8', '9', '10', '11',
							'12', '14', '16', '18',
							'20', '22', '24', '28',
							'30', '36', '50', '72'],
						disableDragAndDrop: true
						// 드래그 앤 드롭 비활성화
					});
		});