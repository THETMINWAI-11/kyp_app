class ChartHelper {
	
	constructor(type) {
		this.type = type;
		this.title = {diplay:false};
	}
	
	static addTitle(title, options) {
		options.title = {display: true, text: title}
	}
	
	static disableLegend(options) {
		options.legend = {display: false}
	}
	
	static createLineData(datas, labels) {
		const datasets = [];
		for(const data in datas) {
			const temp = {
      			label: 'Cubic interpolation (monotone)',
     			data: [5,7,3,5, 9],
      			borderColor: 'red',
      			fill: false,
      			cubicInterpolationMode: 'monotone',
    		}
			datasets.push(temp);
		}
		
		return {labels, datasets};
		 
	}
	
	createOptions() {
		return {
			type : this.type,
			title : this.title
		}
	}
}