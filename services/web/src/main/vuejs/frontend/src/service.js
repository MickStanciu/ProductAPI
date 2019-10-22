import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080/api/tradelog/d79ec11a-2011-4423-ba01-3af8de0a3e14';

const appService = {
    getTradedSymbols() {
        return new Promise(resolve => {
            axios
                .get('/symbols')
                .then(response => {
                    resolve(response.data)
                })
                .catch(error => {
                    console.error(error);
                })
        });
    },

    getSummary() {
        return new Promise(resolve => {
            axios
                .get('/summary')
                .then(response => {
                    resolve(response.data)
                })
                .catch(error => {
                    console.error(error);
            })
        });
    },

    getShareData(symbol) {
        return new Promise(resolve => {
            axios
                .get('/share/data/' + symbol.toUpperCase())
                .then(response => {
                    resolve(response.data);
                })
                .catch(error => {
                    console.log(error);
            })
        });
    },

    getTradesPerSymbol(symbol) {
        return new Promise(resolve => {
            axios
                .get('/trades/' + symbol.toUpperCase())
                .then(response => {
                    resolve(response.data);
                })
                .catch(error => {
                    console.error(error);
                })
            });
    },

    recordShareTrade(dto) {
        //TODO: bug -> delete takes longer and refresh occurs before that ... need to wait
        return new Promise(resolve => {
            axios
                .post('/share', dto)
                .then(response => {
                    resolve(response.data);
                })
                .catch(error => {
                    console.error(error);
                    resolve(null);
            })
        });
    },

    deleteShareTrade(dto) {
        //TODO: bug -> delete takes longer and refresh occurs before that ... need to wait
        return new Promise(resolve => {
            axios
                .delete('/share/' + dto.symbol + '/' + dto.transactionId)
                .catch(error => {
                    console.error(error);
                    resolve(null);
                })
        })
    },

    recordOptionTrade(dto) {
        //TODO: bug -> delete takes longer and refresh occurs before that ... need to wait
        return new Promise(resolve => {
            axios
                .post('/option', dto)
                .then(response => {
                    resolve(response.data);
                })
                .catch(error => {
                    console.error(error);
                    resolve(null);
            })
        });
    },

    deleteOptionTrade(dto) {
        //TODO: bug -> delete takes longer and refresh occurs before that ... need to wait
        return new Promise(resolve => {
            axios
                .delete('/option/' + dto.stockSymbol + '/' + dto.transactionId)
                .catch(error => {
                    console.error(error);
                    resolve(null);
                })
        })
    },

    recordDividendTrade(dto) {
        //TODO: bug -> delete takes longer and refresh occurs before that ... need to wait
        return new Promise(resolve => {
            axios
                .post('/dividend', dto)
                .then(response => {
                    resolve(response.data);
                })
                .catch(error => {
                    console.error(error);
                    resolve(null);
                });
        })
    },

    deleteDividendRecord(dto) {
        //TODO: bug -> delete takes longer and refresh occurs before that ... need to wait
        return new Promise(resolve => {
            axios
                .delete('/dividend/' + dto.symbol + '/' + dto.transactionId)
                .catch(error => {
                    console.error(error);
                    resolve(null);
                })
        })
    },

    saveSettings(settings) {
        return new Promise(resolve => {
           axios.put('/settings/bulk', settings)
               .then(response => {
                   console.debug("Bulk Settings saved");
               })
               .catch(error => {
                   console.error(error);
                   resolve(null);
               })
        });
    },

    saveSetting(setting) {
        return new Promise(resolve => {
            axios.put('/settings/' + setting.transactionId, setting)
                .then(response => {
                    console.debug("Settings saved");
                })
                .catch(error => {
                    console.error(error);
                    resolve(null);
                })
        });
    }
};

export default appService;