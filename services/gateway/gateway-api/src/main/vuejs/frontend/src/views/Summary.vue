<template>
  <div id="summary">
      <div id="summary-options-dividends">
        <h2>Options and Dividends Cashflow</h2>
        <div class="row mt-3 pb-2 pt-2 table-header">
          <div class="col">Month</div>
          <div class="col" v-for="(key, idx) in optionAndDividendKeys" v-bind:key="idx">{{key}}</div>
        </div>
        <div class="row table-cell" v-for="(month, monthIdx) in months" v-bind:key="monthIdx">
          <div class="col month-header">{{month}}</div>
          <div class="col" v-for="(year, yearIdx) in optionAndDividendKeys" v-bind:key="yearIdx">{{printCurrencyFormat(getMonthlyTotal(year, monthIdx + 1, optionAndDividendItems))}}</div>
        </div>

        <div class="row pb-1 pt-1 table-footer">
          <div class="col">TOTAL:</div>
          <div class="col" v-for="(year, yearIdx) in optionAndDividendKeys" v-bind:key="yearIdx">{{printCurrencyFormat(getYearlyTotal(year, optionAndDividendItems))}}</div>
        </div>
    </div>

    <div class="row pt-4">&nbsp;</div>
    <div id="summary-shares">
      <h2>Shares Cashflow</h2>
      <div class="row mt-3 pb-2 pt-2 table-header">
        <div class="col">Month</div>
        <div class="col" v-for="(key, idx) in shareKeys" v-bind:key="idx">{{key}}</div>
      </div>
      <div class="row table-cell" v-for="(month, monthIdx) in months" v-bind:key="monthIdx">
        <div class="col month-header">{{month}}</div>
        <div class="col" v-for="(year, yearIdx) in shareKeys" v-bind:key="yearIdx">{{printCurrencyFormat(getMonthlyTotal(year, monthIdx + 1, shareItems))}}</div>
      </div>

      <div class="row pb-1 pt-1 table-footer">
        <div class="col">TOTAL:</div>
        <div class="col" v-for="(year, yearIdx) in shareKeys" v-bind:key="yearIdx">{{printCurrencyFormat(getYearlyTotal(year, shareItems))}}</div>
      </div>
    </div>

  </div>
</template>

<script>
import service from '../service';
import router from "../router";

export default {
        name: "Summary",
        data: function () {
            return {
              optionAndDividendItems: {},
              optionAndDividendItemsLoaded: false,
              optionAndDividendKeys: [],
              shareItems: {},
              shareItemsLoaded: false,
              shareKeys: [],
              months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August',
                'September', 'October', 'November', 'December'],
            }
        },
        methods: {
          getMonthlyTotal(year, month, collection) {
            if (year in collection) {
              let monthData = collection[year];
              if (month in monthData) {
                let total = monthData[month];
                // console.debug(year + "-" + month + "-" + total);
                return total;
              }
            }
            return 0
          },
          getYearlyTotal(year, collection) {
            if (year in collection) {
              let monthData = collection[year];
              if ('total' in monthData) {
                return monthData['total']
              }
              console.debug("Error calculating yearly total");
              return 0.00
            }
          },
          printCurrencyFormat: function (value) {
            if (typeof value === 'undefined') {
              value = 0;
            }
            let params = {
              style: 'currency',
              currency: 'USD',
              minimumFractionDigits: 2
            };
            return new Intl.NumberFormat('en-US', params).format(value);
          }
        },
        created() {
            console.debug("CREATED");
            let model = JSON.parse(localStorage.getItem('auth'));
            console.debug(model);
            if (model === null) {
                router.push('/login');
                return
            }

            if (this.$store.state.auth.isAuthenticated === false) {
                if ("undefined" !== typeof model && "undefined" !== typeof(model["api_token"])) {
                    this.$store.dispatch('auth/success', model);
                } else {
                    router.push('/login');
                    return
                }
            }

            service
                .getSummaryMatrix(model["api_token"], false)
                .then(data => {
                    //NOTE: months index starts with 1 (January). Not 0
                    let self = this;
                    let yearItems = {};

                    // console.debug(data.years)
                    for (const year in data.years) {
                        if (data.years.hasOwnProperty(year)) {
                            const yearData = data.years[year];
                            // console.debug(yearData);

                            let totalYear = yearData.total;
                            if (typeof(totalYear) === 'undefined') {
                                totalYear = 0;
                            }

                            if (!(year in yearItems)) {
                                let monthItem = {};
                                for (let i = 1; i <= 12; i++) {
                                    monthItem[i] = 0.00;
                                }
                                monthItem['total'] = totalYear;
                                yearItems[year] = monthItem
                            }

                            for (const month in yearData.months) {
                                if (yearData.months.hasOwnProperty(month)) {
                                    let monthTotal = yearData.months[month];
                                    if (typeof(monthTotal) === 'undefined') {
                                        monthTotal = 0;
                                    }

                                    let monthItems = yearItems[year];
                                    monthItems[month] += monthTotal
                                }

                            }
                        }
                    }
                    self.optionAndDividendItems = yearItems;
                    self.optionAndDividendItemsLoaded = true;

                    for (let key in yearItems) {
                        self.optionAndDividendKeys.push(key);
                    }
                })

          service
              .getSummaryMatrix(model["api_token"], true)
              .then(data => {
                //NOTE: months index starts with 1 (January). Not 0
                let self = this;
                let yearItems = {};

                // console.debug(data.years)
                for (const year in data.years) {
                  if (data.years.hasOwnProperty(year)) {
                    const yearData = data.years[year];
                    // console.debug(yearData);

                    let totalYear = yearData.total;
                    if (typeof(totalYear) === 'undefined') {
                      totalYear = 0;
                    }

                    if (!(year in yearItems)) {
                      let monthItem = {};
                      for (let i = 1; i <= 12; i++) {
                        monthItem[i] = 0.00;
                      }
                      monthItem['total'] = totalYear;
                      yearItems[year] = monthItem
                    }

                    for (const month in yearData.months) {
                      if (yearData.months.hasOwnProperty(month)) {
                        let monthTotal = yearData.months[month];
                        if (typeof(monthTotal) === 'undefined') {
                          monthTotal = 0;
                        }

                        let monthItems = yearItems[year];
                        monthItems[month] += monthTotal
                      }

                    }
                  }
                }
                self.shareItems = yearItems;
                self.shareItemsLoaded = true;

                for (let key in yearItems) {
                  self.shareKeys.push(key);
                }
              })
        },
        mounted() {
            //try local storage auth
            console.debug("MOUNTED");
        }
    }

</script>

<style scoped>
    #summary {
        font-size: 0.75rem;
    }

    .table-header {
        background-color: #005cbf;
        color: white;
        vertical-align: center;
        border: #005cbf 1px solid;
        font-weight: bold;
    }

    .table-footer {
        background-color: #9fcdff;
        color: #005cbf;
        font-weight: bold;
        vertical-align: center;
        border: #9fcdff 1px solid;
    }

    .table-cell {
        background-color: whitesmoke;
    }

    .table-cell .month-header {
        font-weight: bold;
    }
</style>