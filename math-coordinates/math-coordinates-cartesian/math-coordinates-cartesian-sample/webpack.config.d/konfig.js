config.resolve.modules.push("/media/andylamax/63C23C360914D355/aSoft/OSS Libs/math/code/math-coordinates/math-coordinates-cartesian/math-coordinates-cartesian-sample/build/resources/main")
config.module.rules.push({
    test: /\.(png|jpe?g|gif|svg)$/i,
    use: [
      {
        loader: 'file-loader',
      },
    ],
});
config.devServer = { ...config.devServer, historyApiFallback: true, host: "0.0.0.0" }