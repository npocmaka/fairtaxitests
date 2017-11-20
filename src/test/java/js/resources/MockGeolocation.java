package js.resources;

public class MockGeolocation {
	
	public static String mockGeoloc="(function(window, navigator) {\n"+
			" var exports = {},\n"+
			" counterId = 0,\n"+
			" successData = {\n"+
			" accuracy: 50,\n"+
			" altitude: null,\n"+
			" altitudeAccuracy: null,\n"+
			" heading: null,\n"+
			" latitude: 54.9799,\n"+
			" longitude: 82.89683699999999,\n"+
			" speed: null\n"+
			" },\n"+
			" errorData = {\n"+
			" code: 1,\n"+
			" message: \'The acquisition of the geolocation information failed because the page didn\\\'t have the permission to do it.\'\n"+
			" };\n"+
			"\n"+
			" var getCurrentPositionArguments = {},\n"+
			" watchPositionArguments = {},\n"+
			" _navigatorGeolocation;\n"+
			"\n"+
			" var changeGeolocation = function(object) {\n"+
			" if (Object.defineProperty) {\n"+
			" Object.defineProperty(navigator, \'geolocation\', {\n"+
			" get: function() {\n"+
			" return object;\n"+
			" },\n"+
			" configurable: true\n"+
			" });\n"+
			" } else if (navigator.__defineGetter__) {\n"+
			" navigator.__defineGetter__(\'geolocation\', function() {\n"+
			" return object;\n"+
			" });\n"+
			" } else {\n"+
			" throw new Error(\'Cannot change navigator.geolocation method\');\n"+
			" }\n"+
			" };\n"+
			"\n"+
			" exports.use = function() {\n"+
			" _navigatorGeolocation = navigator.geolocation;\n"+
			"\n"+
			" changeGeolocation({\n"+
			" getCurrentPosition: function(success, error, opt) {\n"+
			" counterId++;\n"+
			" getCurrentPositionArguments[counterId] = arguments;\n"+
			" },\n"+
			" watchPosition: function(success, error, opt) {\n"+
			" counterId++;\n"+
			" getCurrentPositionArguments[counterId] = arguments;\n"+
			" watchPositionArguments[counterId] = arguments;\n"+
			" return counterId;\n"+
			" },\n"+
			" clearWatch: function(i) {\n"+
			" if (watchPositionArguments[i]) {\n"+
			" delete watchPositionArguments[i];\n"+
			"\n"+
			" if (getCurrentPositionArguments[i]) {\n"+
			" delete getCurrentPositionArguments[i];\n"+
			" }\n"+
			" }\n"+
			" }\n"+
			" });\n"+
			"\n"+
			" return this;\n"+
			" };\n"+
			"\n"+
			" exports.restore = function() {\n"+
			" if (_navigatorGeolocation) {\n"+
			" changeGeolocation(_navigatorGeolocation);\n"+
			" } else {\n"+
			" delete navigator.geolocation;\n"+
			" }\n"+
			"\n"+
			" getCurrentPositionArguments = {};\n"+
			" watchPositionArguments = {};\n"+
			"\n"+
			" return this;\n"+
			" };\n"+
			"\n"+
			" function changeSuccessData(options) {\n"+
			" // copy available parameter to successData\n"+
			" for (var i in successData) {\n"+
			" if (options.hasOwnProperty(i)) {\n"+
			" successData[i] = options[i];\n"+
			" }\n"+
			" }\n"+
			"\n"+
			" // lat and lng are available parameters too\n"+
			" if (options.lat !== undefined) {\n"+
			" successData.latitude = options.lat;\n"+
			" }\n"+
			" if (options.lng !== undefined) {\n"+
			" successData.longitude = options.lng;\n"+
			" }\n"+
			" }\n"+
			"\n"+
			" function getRequestData(options) {\n"+
			" options = options || {};\n"+
			"\n"+
			" var data = {\n"+
			" coords: {}\n"+
			" };\n"+
			"\n"+
			" for (var i in successData) {\n"+
			" data.coords[i] = successData[i];\n"+
			" }\n"+
			"\n"+
			" if (options.timestamp !== undefined) {\n"+
			" data.timestamp = options.timestamp;\n"+
			" } else {\n"+
			" data.timestamp = Date.now();\n"+
			" }\n"+
			"\n"+
			" return data;\n"+
			" }\n"+
			"\n"+
			" function getErrorData(options) {\n"+
			" options = options || {};\n"+
			"\n"+
			" var data = {};\n"+
			"\n"+
			" if (options.code !== undefined) {\n"+
			" data.code = options.code;\n"+
			" } else {\n"+
			" data.code = errorData.code;\n"+
			" }\n"+
			"\n"+
			" if (options.message !== undefined) {\n"+
			" data.message = options.message;\n"+
			" } else {\n"+
			" data.message = errorData.message;\n"+
			" }\n"+
			"\n"+
			" return data;\n"+
			" }\n"+
			"\n"+
			" exports.send = function(options) {\n"+
			" if (options) {\n"+
			" changeSuccessData(options);\n"+
			" }\n"+
			"\n"+
			" for (var i in getCurrentPositionArguments) {\n"+
			" if (typeof getCurrentPositionArguments[i][0] === \'function\') {\n"+
			" getCurrentPositionArguments[i][0](getRequestData(options));\n"+
			" }\n"+
			" }\n"+
			"\n"+
			" getCurrentPositionArguments = {};\n"+
			"\n"+
			" return this;\n"+
			" };\n"+
			"\n"+
			" exports.sendError = function(options) {\n"+
			" for (var i in getCurrentPositionArguments) {\n"+
			" if (typeof getCurrentPositionArguments[i][1] === \'function\') {\n"+
			" getCurrentPositionArguments[i][1](getErrorData(options));\n"+
			" }\n"+
			" }\n"+
			"\n"+
			" getCurrentPositionArguments = {};\n"+
			"\n"+
			" return this;\n"+
			" };\n"+
			"\n"+
			" exports.change = function(options) {\n"+
			" if (options) {\n"+
			" changeSuccessData(options);\n"+
			" }\n"+
			"\n"+
			" for (var i in watchPositionArguments) {\n"+
			" if (typeof watchPositionArguments[i][0] === \'function\') {\n"+
			" watchPositionArguments[i][0](getRequestData(options));\n"+
			" }\n"+
			"\n"+
			" if (getCurrentPositionArguments[i]) {\n"+
			" delete getCurrentPositionArguments[i];\n"+
			" }\n"+
			" }\n"+
			"\n"+
			" return this;\n"+
			" };\n"+
			"\n"+
			" exports.changeError = function(options) {\n"+
			" for (var i in watchPositionArguments) {\n"+
			" if (typeof watchPositionArguments[i][1] === \'function\') {\n"+
			" watchPositionArguments[i][1](getErrorData(options));\n"+
			" }\n"+
			"\n"+
			" if (getCurrentPositionArguments[i]) {\n"+
			" delete getCurrentPositionArguments[i];\n"+
			" }\n"+
			" }\n"+
			"\n"+
			" return this;\n"+
			" };\n"+
			"\n"+
			" function expose() {\n"+
			" var _geolocate = window.geolocate;\n"+
			"\n"+
			" exports.noConflict = function() {\n"+
			" if (_geolocate !== undefined) {\n"+
			" window.geolocate = _geolocate;\n"+
			" } else {\n"+
			" delete window.geolocate;\n"+
			" }\n"+
			"\n"+
			" return exports;\n"+
			" };\n"+
			"\n"+
			" window.geolocate = exports;\n"+
			" }\n"+
			"\n"+
			" // define for Node module pattern loaders, including Browserify\n"+
			" if (typeof module === \'object\' && typeof module.exports === \'object\') {\n"+
			" module.exports = exports;\n"+
			"\n"+
			" // define as an AMD module\n"+
			" } else if (typeof define === \'function\' && define.amd) {\n"+
			" define(exports);\n"+
			"\n"+
			" // define as a global variable, saving the original to restore later if needed\n"+
			" } else if (typeof window !== \'undefined\') {\n"+
			" expose();\n"+
			" }\n"+
			"})(window, navigator);";
	
	//https://raw.githubusercontent.com/2gis/mock-geolocation/master/src/geolocate.js
	/*
	 (function(window, navigator) {
    var exports = {},
        counterId = 0,
        successData = {
            accuracy: 50,
            altitude: null,
            altitudeAccuracy: null,
            heading: null,
            latitude: 54.9799,
            longitude: 82.89683699999999,
            speed: null
        },
        errorData = {
            code: 1,
            message: 'The acquisition of the geolocation information failed because the page didn\'t have the permission to do it.'
        };

    var getCurrentPositionArguments = {},
        watchPositionArguments = {},
        _navigatorGeolocation;

    var changeGeolocation = function(object) {
        if (Object.defineProperty) {
            Object.defineProperty(navigator, 'geolocation', {
                get: function() {
                    return object;
                },
                configurable: true
            });
        } else if (navigator.__defineGetter__) {
            navigator.__defineGetter__('geolocation', function() {
                return object;
            });
        } else {
            throw new Error('Cannot change navigator.geolocation method');
        }
    };

    exports.use = function() {
        _navigatorGeolocation = navigator.geolocation;

        changeGeolocation({
            getCurrentPosition: function(success, error, opt) {
                counterId++;
                getCurrentPositionArguments[counterId] = arguments;
            },
            watchPosition: function(success, error, opt) {
                counterId++;
                getCurrentPositionArguments[counterId] = arguments;
                watchPositionArguments[counterId] = arguments;
                return counterId;
            },
            clearWatch: function(i) {
                if (watchPositionArguments[i]) {
                    delete watchPositionArguments[i];

                    if (getCurrentPositionArguments[i]) {
                        delete getCurrentPositionArguments[i];
                    }
                }
            }
        });

        return this;
    };

    exports.restore = function() {
        if (_navigatorGeolocation) {
            changeGeolocation(_navigatorGeolocation);
        } else {
            delete navigator.geolocation;
        }

        getCurrentPositionArguments = {};
        watchPositionArguments = {};

        return this;
    };

    function changeSuccessData(options) {
        // copy available parameter to successData
        for (var i in successData) {
            if (options.hasOwnProperty(i)) {
                successData[i] = options[i];
            }
        }

        // lat and lng are available parameters too
        if (options.lat !== undefined) {
            successData.latitude = options.lat;
        }
        if (options.lng !== undefined) {
            successData.longitude = options.lng;
        }
    }

    function getRequestData(options) {
        options = options || {};

        var data = {
            coords: {}
        };

        for (var i in successData) {
            data.coords[i] = successData[i];
        }

        if (options.timestamp !== undefined) {
            data.timestamp = options.timestamp;
        } else {
            data.timestamp = Date.now();
        }

        return data;
    }

    function getErrorData(options) {
        options = options || {};

        var data = {};

        if (options.code !== undefined) {
            data.code = options.code;
        } else {
            data.code = errorData.code;
        }

        if (options.message !== undefined) {
            data.message = options.message;
        } else {
            data.message = errorData.message;
        }

        return data;
    }

    exports.send = function(options) {
        if (options) {
            changeSuccessData(options);
        }

        for (var i in getCurrentPositionArguments) {
            if (typeof getCurrentPositionArguments[i][0] === 'function') {
                getCurrentPositionArguments[i][0](getRequestData(options));
            }
        }

        getCurrentPositionArguments = {};

        return this;
    };

    exports.sendError = function(options) {
        for (var i in getCurrentPositionArguments) {
            if (typeof getCurrentPositionArguments[i][1] === 'function') {
                getCurrentPositionArguments[i][1](getErrorData(options));
            }
        }

        getCurrentPositionArguments = {};

        return this;
    };

    exports.change = function(options) {
        if (options) {
            changeSuccessData(options);
        }

        for (var i in watchPositionArguments) {
            if (typeof watchPositionArguments[i][0] === 'function') {
                watchPositionArguments[i][0](getRequestData(options));
            }

            if (getCurrentPositionArguments[i]) {
                delete getCurrentPositionArguments[i];
            }
        }

        return this;
    };

    exports.changeError = function(options) {
        for (var i in watchPositionArguments) {
            if (typeof watchPositionArguments[i][1] === 'function') {
                watchPositionArguments[i][1](getErrorData(options));
            }

            if (getCurrentPositionArguments[i]) {
                delete getCurrentPositionArguments[i];
            }
        }

        return this;
    };

    function expose() {
        var _geolocate = window.geolocate;

        exports.noConflict = function() {
            if (_geolocate !== undefined) {
                window.geolocate = _geolocate;
            } else {
                delete window.geolocate;
            }

            return exports;
        };

        window.geolocate = exports;
    }

    // define for Node module pattern loaders, including Browserify
    if (typeof module === 'object' && typeof module.exports === 'object') {
        module.exports = exports;

    // define as an AMD module
    } else if (typeof define === 'function' && define.amd) {
        define(exports);

    // define as a global variable, saving the original to restore later if needed
    } else if (typeof window !== 'undefined') {
        expose();
    }
})(window, navigator);
	 */

}
