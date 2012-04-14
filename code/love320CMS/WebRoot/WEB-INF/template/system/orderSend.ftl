<form method="post" action="${base}/html/onlineorder!save.action" class="pageForm required-validate">
	<div class="pageFormContent" layouth="56">
		<p>
			<label>产品名称：</label><input type="text" name="prpName" size="30" id="prpName" value="" />
		</p>
		<p>
			<label>期望价格：</label><input class="number" type="text" name="money" size="30" id="money" value="" />
		</p>
		<p>
			<label>定购数量：</label><input class="digits" type="text" name="num" size="30" id="num" value="" />
		</p>
		<p>
			<label>公司名称：</label><input type="text" name="company" size="30" id="company" value="" />
		</p>
		<p>
			<label>联系电话：</label><input class="phone" type="text" name="tel" size="30" id="tel" value="" />
		</p>
		<p>
			<label>联系人：</label><input type="text" name="perple" size="30" id="perple" value="" />
		</p>
		<p>
			<label>联系地址：</label><input type="text" name="address" size="30" id="address" value="" />
		</p>
		<p>
			<label>邮政编码：</label><input type="text" name="postalCode" size="30" id="postalCode" value="" />
		</p>
		<p>
			<label>联系Email：</label><input class="email" type="text" name="email" size="30" id="email" value="" />
		</p>
		<p>
			<label>公 司 网 址：</label><input class="url" type="text" name="webSite" size="30" id="webSite" value="" />
		</p>
		<div class="divider">
		</div>
		<label>订购要求和其他：</label><textarea name="test" cols="80" rows="3"></textarea>
		<div class="divider">
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="buttonActive">
					<div class="buttonContent">
						<button type="submit">
							保存
						</button>
					</div>
				</div>
			</li>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" class="close">
							取消
						</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</form>