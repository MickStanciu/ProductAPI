<template>
    <div class="modal" id="syntheticPriceModal">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">Edit Synthetic for {{form_element.symbol}}</h3>
                </div>

                <div class="modal-body">
                    <form>
                        <fieldset class="form-group row">
                            <label for="price" class="col-sm-3 col-form-label" v-bind:class="{'text-danger': form_validation.price === false}">Price:</label>
                            <div class="col-sm-9">
                                <input class="form-control" v-bind:class="{'is-invalid': form_validation.price === false}" v-model="form_element.price" type="text" id="price"/>
                            </div>
                        </fieldset>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-info" v-on:click="closeModal()"> Close </button>
                    <button type="button" class="btn btn-primary" data-dismiss="modal" v-on:click="submitAndClose()">Submit</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import service from '../../service';
    import validation from "../../utils/validation";
    import SettingsApiModel from "../../models/SettingsApiModel";

    export default {
        name: "SyntheticPrice",
        props: {
            post: Object
        },
        data: function () {
            return {
                token: this.post.token,

                form_element: {
                    symbol : this.post.model[0].symbol,
                    price: this.post.model[0].preferredPrice !== null ? this.post.model[0].preferredPrice : this.post.model[0].price
                },

                form_validation: {
                    price: true,
                    isValid: function () {
                        return this.price
                    }
                }
            }
        },
        methods: {
            closeModal: function () {
                this.$store.dispatch('hideSyntheticShareModal');
            },

            submitAndClose: function () {
                let settings = [];
                for(let i = 0; i < this.post.model.length; i++) {
                    let settingModel = new SettingsApiModel();
                    settingModel.transactionId = this.post.model[i].transactionId;
                    settingModel.groupSelected = this.post.model[i].groupSelected;
                    settingModel.legClosed = this.post.model[i].legClosed;
                    settingModel.preferredPrice = this.form_element.price;

                    if (settingModel.transactionId != null) {
                        settings.push(settingModel);
                    }
                }

                let request = {
                    "items": settings
                };
                service.saveSettings(request, this.token);
                this.$store.dispatch('hideSyntheticShareModal');
                this.$store.dispatch('refreshData');
            },

            checkForm: function() {
                this.form_validation.price = validation.isNumber(this.form_element.price) !== false;
                return this.form_validation.isValid();
            }
        }
    }
</script>

<style scoped>
    .modal {
        display: block;
        font-size: 0.75rem;
    }

    .modal-title {
        font-size: 1.5rem;
    }

    .form-control {
        font-size: 0.75rem;
    }
</style>