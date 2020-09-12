# Voyager

Easily keep track of your vehicle mileage while you are on the go with this simple and easy to use mileage tracking app.

## Installation

// TODO

## Contributing

The following sections describe how to contribute to Voyager. Before continuing, make sure you have cloned the repo.

```sh
git clone git@github.com:mskelton/voyager.git
```

### Mobile app

When developing the mobile app, it is recommended to use [Android Studio](https://developer.android.com/studio) for the best development experience. After downloading and installing Android Studio, open the `mobile` directory to begin developing.

Assuming you have the default Android Studio plugins enabled, everything should just work out of the box without any extra setup required. The first time you open the mobile app in Android Studio it will take a while to sync the Gradle project but after the initial sync you will be good to go.

### API

_The API is not required when developing locally as the mobile app is design with an offline first, network sync architecture. However, it is recommended to run the API to test the full end-to-end functionality of the app._

#### Setup

Before running the API, you will need to install packages with npm.

```sh
npm install
```

#### Running the server

To run the API locally, run the following command to start the API using `nodemon` to watch changes and automatically reload the server.

```sh
npm run watch
```

#### Testing

API integration tests are run with Jest using the following command. Checkout the [Jest documentation](https://jestjs.io/docs/en/cli) for a list of available CLI options.

```sh
npm test
```
