var webpack = require("webpack");
var path = require("path");
module.exports = {
    context: __dirname,
    entry: "./app/assets/javascripts/main.coffee",
    output: {
        path: path.join(__dirname, "public/javascripts"),
        publicPath: "public/", // relative path for github pages
        filename: "build.js", // no hash in main.js because index.html is a static page
    },
    module: {
        loaders: [
            { test: /\.json$/,   loader: "json-loader" },
            { test: /\.coffee$/, loader: "coffee-loader" },
            { test: /\.css$/,    loader: "style-loader!css-loader" },
            { test: /\.less$/,   loader: "style-loader!css-loader!less-loader" }
        ],
        preLoaders: [
            {
                test: /\.js$/,
                include: pathToRegExp(path.join(__dirname, "app")),
                loader: "jshint-loader"
            }
        ]
    },
    resolve: {
        fallback: path.join(__dirname, "jam")
    },
    amd: { jQuery: true },
    plugins: [
        new webpack.optimize.LimitChunkCountPlugin({ maxChunks: 20 })
    ],
    fakeUpdateVersion: 0
};
function escapeRegExpString(str) { return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&"); }
function pathToRegExp(p) { return new RegExp("^" + escapeRegExpString(p)); }